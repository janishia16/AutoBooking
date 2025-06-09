

import { Outlet } from 'react-router-dom';
import { Bookings } from '../Components/Bookings.js';


export default function BookingsPage() {
  return (
      <main>
        <Bookings />
        <Outlet/>
      </main>
  );
}
