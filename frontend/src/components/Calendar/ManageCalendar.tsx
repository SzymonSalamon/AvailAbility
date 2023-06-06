import React, { Component, RefObject, useEffect, useState, useRef, useCallback} from 'react';
import { DayPilot, DayPilotCalendar, DayPilotNavigator } from "@daypilot/daypilot-lite-react";
import "./CalendarStyles.css";
import fetchApi from '../../utils/fetchApi';

interface Shift {
  id: number;
  title: string;
  start: Date;
  end: Date;
}

interface CalendarProps {
  startDate: Date;
  view: string;
  events: Shift[];
  children: React.ReactNode;
  onEventsChange: (newEvents: Shift[]) => void;
}

interface CalendarState {
  viewType: string;
  durationBarVisible: boolean;
  timeRangeSelectedHandling: string;
}

class Calendar extends Component<CalendarProps, CalendarState> {
  private calendarRef: RefObject<DayPilotCalendar>;

  constructor(props: CalendarProps) {
    super(props);
    this.calendarRef = React.createRef<DayPilotCalendar>();
    this.state = {
      viewType: "Week",
      durationBarVisible: false,
      timeRangeSelectedHandling: "Enabled",
    };
  }

  private get calendar(): DayPilot.Calendar {
    return this.calendarRef.current!.control;
  }

  private onTimeRangeSelected = async (args: DayPilot.TimeRangeSelectedEventArgs) => {
    const dp = this.calendar;
    const modal = await DayPilot.Modal.prompt("Create a new Shift:", "Zmiana");
    dp.clearSelection();
    if (!modal.result) { return; }

    const newEvent: Shift = {
      id: -1,
      title: modal.result,
      start: args.start,
      end: args.end,
    };

    dp.events.add(newEvent);
    this.props.onEventsChange([...this.props.events, newEvent]);
  };

 private onDeleteEvent = async (args: DayPilot.EventClickArgs) => {
    const dp = this.calendar;
    const e = args.e;
    
    // Update the event's title and make it invisible
    e.data.text = "TO_DELETE";
    e.data.visible = false;
    dp.events.update(e);

    const updatedEvents = this.props.events.map(shift => {
      if(shift.id.toString() === e.id()) {
        return {
          ...shift,
          title: "TO_DELETE"
        }
      }
      return shift;
    });

    this.props.onEventsChange(updatedEvents);
  };


  render() {
    return (
      <div style={{ display: "flex", height:"100%"}}>
        <div className="hidden sm:block" style={{ marginRight: "10px" }}>
          <DayPilotNavigator
            theme={"cal_dark"}
            selectMode={"week"}
            showMonths={2}
            skipMonths={2}
            startDate={this.props.startDate}
            selectionDay={this.props.startDate}
            onTimeRangeSelected={(args: any) => {
              this.calendar.update({
                startDate: args.day
              });
            }}
          />
        </div>
        <div style={{ flexGrow: "1" }}>
          <DayPilotCalendar
            events={this.props.events.map(shift => ({
              id: shift.id.toString(),
              text: shift.title,
              start: shift.start,
              end: shift.end,
            }))}
            theme={"dark_theme"}
            {...this.state}
            ref={this.calendarRef}
            onTimeRangeSelected={this.onTimeRangeSelected}
            onEventClick={this.onDeleteEvent}
          />
          <div>{this.props.children}</div>
        </div>
      </div>
    );
  }
}

function ShiftCalendar({ username }: { username: string | null }): JSX.Element {
  const [shifts, setShifts] = useState<Shift[]>([]);
  const lastClickedEventRef = useRef<DayPilot.EventObject | null>(null);
  const [initialLoad, setInitialLoad] = useState(true);

  const fetchShifts = async (username: string | null | undefined) => {
    try {
      const response = await fetchApi(`/shifts/${username}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      const data = await response.json();
      setShifts(data);
    } catch (error) {
      console.error('Error fetching shifts:', error);
    }
  };

  useEffect(() => {
    fetchShifts(username);
  }, [username]);

  const onEventsChange = async (newEvents: Shift[]) => {
    // Update the local state immediately
    setShifts(newEvents);
  
    try {
      await fetchApi(`/addshifts/${username}`, { // Assuming 'username' is accessible in this scope
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newEvents),
      });
    } catch (error) {
      console.error('Error updating shifts:', error);
    } finally {
      // Fetch shifts again to ensure local state matches the server's state
      fetchShifts(username);
    }
  };

  const startDate = shifts.reduce((current, shift) => {
    return shift.start < current ? shift.start : current;
  }, new Date());

  return (
    <div className='h-full'>
      <Calendar
        startDate={initialLoad ? new Date() : startDate}
        view="Week"
        events={shifts}
        onEventsChange={onEventsChange}
      >
       
      </Calendar>
    </div>
  );
}



export default ShiftCalendar;
