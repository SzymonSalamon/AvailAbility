import React, { Component, RefObject, useEffect, useState } from 'react';
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
    const modal = await DayPilot.Modal.prompt("Create a new event:", "Zmiana");
    dp.clearSelection();
    if (!modal.result) { return; }
    dp.events.add({
      start: args.start,
      end: args.end,
      id: DayPilot.guid(),
      text: modal.result
    });
  };

  private onBeforeEventRender = (args: DayPilot.BeforeEventRenderEventArgs) => {
    args.data.areas = [
      { right: 0, bottom: 36, html: "<label class='container'><input type='checkbox' checked='checked' name='accept." + args.data.id + "'/><div class='checkmark green'></div>" },
      { right: 0, bottom: 6, html: "<label class='container'><input type='checkbox' name='notaccept." + args.data.id + "'/><div class='checkmark red'></div>" },
    ];
  };

  private onEventClick = async (args: DayPilot.EventClickEventArgs) => {
    const dp = this.calendar;
    const modal = await DayPilot.Modal.prompt("Update event text:", args.e.text());
    if (!modal.result) { return; }
    const e = args.e;
    e.data.text = modal.result;
    dp.events.update(e);
  };

  render() {
    return (
      <div style={{ display: "flex" }}>
        <div style={{ marginRight: "10px" }}>
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
            theme={"dark_theme"}
            {...this.state}
            ref={this.calendarRef}
            onTimeRangeSelected={this.onTimeRangeSelected}
            onBeforeEventRender={this.onBeforeEventRender}
            onEventClick={this.onEventClick}
          />
          <div>{this.props.children}</div>
        </div>
      </div>
    );
  }
}

function ShiftCalendar(): JSX.Element {
  const [shifts, setShifts] = useState<Shift[]>([]);

  useEffect(() => {
    fetch('/api/shifts')
      .then(response => response.json())
      .then(data => {
        setShifts(data);
      })
      .catch(error => {
        console.error('Error fetching shifts:', error);
      });
  }, []);

  const addShiftsToDatabase = (): void => {
    fetchApi('/addshifts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ shifts }),
    })
      .then(response => {
        if (response.ok) {
          console.log('Shifts added successfully');
        } else {
          throw new Error('Error adding shifts');
        }
      })
      .catch(error => {
        console.error('Error adding shifts:', error);
        console.log(shifts);
      });
  };

  return (
    <div>
      <Calendar startDate={new Date()} view="Week" events={shifts}>
        <button className="bg-white" onClick={addShiftsToDatabase}>Add Shifts to Database</button>
      </Calendar>
    </div>
  );
}

export default ShiftCalendar;
