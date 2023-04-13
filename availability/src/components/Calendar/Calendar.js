
import React, {Component} from 'react';
import {DayPilot, DayPilotCalendar, DayPilotNavigator} from "@daypilot/daypilot-lite-react";
import "./CalendarStyles.css";

const styles = {
  wrap: {
    display: "flex"
    
  },
  left: {
    marginRight: "10px"
  },
  main: {
    flexGrow: "1"
  }
};

class Calendar extends Component {

  constructor(props) {
    super(props);
    this.calendarRef = React.createRef();
    this.state = {
      viewType: "Week",
      durationBarVisible: false,
      timeRangeSelectedHandling: "Enabled",
      onTimeRangeSelected: async args => {
        const dp = this.calendar;
        const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
        dp.clearSelection();
        if (!modal.result) { return; }
        dp.events.add({
          start: args.start,
          end: args.end,
          id: DayPilot.guid(),
          text: modal.result
        });
      },
     /* 
  "<input type=radio name="+ args.data.id +"/>"
</label>*/
      onBeforeEventRender: args => {
        args.data.areas = [
          {right: 0, bottom: 36, html: "<label class='container'><input type='checkbox' checked='checked' name='accept."+ args.data.id +"'/><div class='checkmark green'></div>"},
          {right: 0, bottom: 6, html: "<label class='container'><input type='checkbox' name='notaccept."+ args.data.id +"'/><div class='checkmark red'></div>"},
          ];
      },
    
      eventDeleteHandling: "Update",
      onEventClick: async args => {
        const dp = this.calendar;
        const modal = await DayPilot.Modal.prompt("Update event text:", args.e.text());
        if (!modal.result) { return; }
        const e = args.e;
        e.data.text = modal.result;
        dp.events.update(e);
      },
    };
  }

  get calendar() {
    return this.calendarRef.current.control;
  }

  componentDidMount() {

    const events = [
      {
        id: 1,
        text: " ",
        start: "2023-03-07T10:30:00",
        end: "2023-03-07T13:00:00"
      },
      {
        id: 2,
        text: " ",
        start: "2023-03-08T09:30:00",
        end: "2023-03-08T11:30:00",
      },
      {
        id: 3,
        text: " ",
        start: "2023-03-08T12:00:00",
        end: "2023-03-08T15:00:00",
      },
      {
        id: 4,
        text: " ",
        start: "2023-03-06T11:30:00",
        end: "2023-03-06T14:30:00",
      },
    ];
    const now = new Date();
    const startDate = now.toLocaleDateString();

    this.calendar.update({startDate, events});
  }

  render() {
    return (
      <div style={styles.wrap}>
        <div style={styles.left}>
          <DayPilotNavigator theme={"cal_dark"}
            selectMode={"week"}
            showMonths={2}
            skipMonths={2}
            startDate={"2023-03-07"}
            selectionDay={"2023-03-07"}
            onTimeRangeSelected={ args => {
              this.calendar.update({
                startDate: args.day
              });
            }}
          />
        
        </div>
        <div style={styles.main}>
        <DayPilotCalendar 
        theme={"dark_theme"}
            {...this.state}
            ref={this.calendarRef}
          />
        </div>
      </div>
    );
  }
}

export default Calendar;
