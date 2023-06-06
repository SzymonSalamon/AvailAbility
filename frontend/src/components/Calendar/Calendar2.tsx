import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import { useEffect, useState } from 'react';

import 'react-big-calendar/lib/css/react-big-calendar.css';

const localizer = momentLocalizer(moment);

const MyCalendar = () => {
  const [events, setEvents] = useState<{ title: string; start: Date; end: Date }[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [newEvent, setNewEvent] = useState({ title: '', start: '', end: '' });

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = () => {
    // Fetch events from the backend
    fetch('/shifts')
      .then((response) => response.json())
      .then((data) => {
        setEvents(data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  };

  const handleAddEvent = () => {
    // Convert start and end strings to Date objects
    const startDate = new Date(newEvent.start);
    const endDate = new Date(newEvent.end);

    // Add the new event to the events array
    setEvents([...events, { title: newEvent.title, start: startDate, end: endDate }]);
    setNewEvent({ title: '', start: '', end: '' });
    setShowModal(false);
  };

  const handleInputChange = (e: { target: { name: any; value: any; }; }) => {
    const { name, value } = e.target;
    setNewEvent((prevEvent) => ({ ...prevEvent, [name]: value }));
  };

  return (
    <div style={{ height: '500px' }}>
      <div style={{ marginBottom: '10px' }}>
        <button onClick={() => setShowModal(true)}>Add Event</button>
      </div>

      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        style={{ height: '100%', padding: '10px' }}
      />

      {showModal && (
        <div
          style={{
            position: 'fixed',
            top: '0',
            left: '0',
            width: '100%',
            height: '100%',
            background: 'rgba(0, 0, 0, 0.5)',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <div
            style={{
              background: 'white',
              padding: '20px',
              borderRadius: '4px',
            }}
          >
            <h2>Add Event</h2>
            <form>
              <div>
                <label>Title:</label>
                <input
                  type="text"
                  name="title"
                  value={newEvent.title}
                  onChange={handleInputChange}
                />
              </div>
              <div>
                <label>Start:</label>
                <input
                  type="datetime-local"
                  name="start"
                  value={newEvent.start}
                  onChange={handleInputChange}
                />
              </div>
              <div>
                <label>End:</label>
                <input
                  type="datetime-local"
                  name="end"
                  value={newEvent.end}
                  onChange={handleInputChange}
                />
              </div>
              <button type="button" onClick={handleAddEvent}>
                Add
              </button>
              <button type="button" onClick={() => setShowModal(false)}>
                Cancel
              </button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default MyCalendar;
