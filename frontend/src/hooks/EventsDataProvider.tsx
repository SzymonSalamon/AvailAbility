import React, { useCallback, useMemo, useState } from "react";
import { Event } from "../types/types";
import getEvents from "./getEvents";

interface EventsDataContextType {
  events: Event[];
  typeQuery: string;
  setTypeQuery: (type: string) => void;
  searchQuery: string;
  setSearchQuery: (search: string) => void;
  collection: "events" | "memories";
  setCollection: (collection: "events" | "memories") => void;
  loading: boolean;
}

const EventsDataContext = React.createContext<EventsDataContextType>(
  {} as EventsDataContextType
);

interface Props {
  children: React.ReactNode;
}

const EventsDataProvider = ({ children }: Props) => {
  const [typeQuery, setTypeQuery] = useState<string>("");
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const [collection, setCollection] = useState<"events" | "memories">("events");

  const loadEvents = useCallback(() => {
    setLoading(true);
    const events = getEvents(typeQuery, searchQuery, collection);
    setLoading(false);
    return events;
  }, [typeQuery, searchQuery, collection]);

  const value = useMemo(() => {
    const eventsData = {
      events: loadEvents(),
      typeQuery,
      setTypeQuery,
      searchQuery,
      setSearchQuery,
      collection,
      setCollection,
      loading,
    } as EventsDataContextType;
    return eventsData;
  }, [typeQuery, searchQuery, loadEvents, collection, loading]);

  return (
    <EventsDataContext.Provider value={value}>
      {children}
    </EventsDataContext.Provider>
  );
};

export { EventsDataProvider };
export const useEvents = () => React.useContext(EventsDataContext);
