import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.css';

import {Intro} from './Components/Intro';
import SignUpUser,{signUpAction} from './Components/SignUpUser';
import SignUpDriver,{signUpDiverAction} from './Components/SignUpDriver';
import { ErrorPage } from './Components/ErorPage';
import Login ,{loginAction} from './Components/Login';
import UserLayout from './pages/UserLayout';
import {Dashboard}  from './Components/Dashboard';
import {logoutAction } from '../src/UI/Slidebar.jsx';
import {checkAuthLoader, tokenLoader} from './util/auth';
import RootLayout from './pages/RootLayout';
import BookSeat from './Components/BookSeat';
import { queryClient } from './util/http.js';
import  BookingsPage  from './pages/Bookings.js';
import { UpdateSeat } from './Components/UpdateSeat.js';
import { UpdateProfile } from './Components/UpdateProfile.js';
import { QueryClientProvider } from '@tanstack/react-query';
import BookSeatsLayout from './pages/BookSeatsLayout.js';
import { AboutUs } from './pages/AboutUs.js';

const router = createBrowserRouter([
  {
    path : '/', 
    element : <RootLayout/>,
    errorElement :  <ErrorPage /> ,
    id : 'root',
    children : [
      {index : true , element : <Intro/>},
      {
        path: "users/",
        element: <UserLayout />, 
        children: [
          {
            path: "user",
            element: <SignUpUser />,
            action: signUpAction,
          },
          {
            path: "login", 
            element: <Login />,
            action: loginAction,
          },
          {
            path: "driver", 
            element : <SignUpDriver/>,
            action: signUpDiverAction,
          },
        ],
      },
      {
        path : 'events',
        element : <Dashboard/>,
        loader : checkAuthLoader,
      },
      {
        path: 'events/book-seat',
        element : <BookSeatsLayout/>,
        loader : checkAuthLoader,
        children : [{
          index: true ,
          element:<BookSeat/>
        }]
      },
      {
        path: 'events/profile',
        element: <UpdateProfile />,
        loader : checkAuthLoader,
      },
      {
        path : 'events/viewBooking',
        element : <BookingsPage />,
        loader : checkAuthLoader,
        children : [
          {
            path : 'update-seat',
            element : <UpdateSeat/>
          }
        ]
      },
      {
        path:'aboutus',
        element : <AboutUs/>
      },
      
      
      { path:'logout/:id' , action :logoutAction},

    ]
  }

])

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router}>
        <div className="App">
        
        </div>
      </RouterProvider>
    </QueryClientProvider>
    
  );
}

export default App;
