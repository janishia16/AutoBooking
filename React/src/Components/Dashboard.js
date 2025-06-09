import React from 'react';
import styles from './static/DashBoard.module.css';
import { useQuery } from '@tanstack/react-query';
import EventItem from './EventItem';
import { fetchData } from '../util/http';
import { Slidebar } from '../UI/Slidebar';
import seatbookImage from '../Image/image.png';
import viewseatImg from '../Image/viewseatImg.png';
export function Dashboard() {

  const id=localStorage.getItem('Id');
  const { data, isPending, isError } = useQuery({
    queryKey: ['userData'] ,
    queryFn: () => fetchData({id})
  });


  const user =localStorage.getItem('User');
  return (
    <>
    <div className={styles.dashboard}>

      <Slidebar displayData={data} page="DashBoard"/>


      <main className={styles.mainContent}>
        <header className={styles.header}>
          <h1>Welcome to Your Dashboard</h1>
        </header>
        <ul>
            {user==='User' && <li><EventItem 
                event={{
                    title :'Book A Seat' , 
                    img:seatbookImage,
                    value : 'Book'
                    
            }}/> </li>}
            
            <li><EventItem 
                event={{
                    title :'View your Seat Details' , 
                    img:viewseatImg,
                    value : 'View Details'
                    
            }}/> </li>
            
        </ul>
      </main>
    </div>
    </>
    
  );
}
