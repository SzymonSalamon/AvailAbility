interface User {
  id: number;
  name: string;
}

interface Event {
  id: number;
  title: string;
  description: string;
  participants: Array<User>;
  coordinates: Array<number>;
  type: string;
  image: string;
}

export type { User, Event };
