import { useQuery } from '@tanstack/react-query';
import {BookingTabs} from './BookingTabs';
import { fetchBookings,fetchRides } from '../util/http';
import { useState } from 'react';
import styles from '../Components/static/Bookings.module.css';
import { BookingItem } from './BookingItem';
import { Slidebar } from '../UI/Slidebar';

export function Bookings(){

    const [selectedType,setSelectedType] = useState('active');
    const [expanded, setExpanded] = useState(null);

    const id = localStorage.getItem('Id');

    //Bookings OR Rides
    const shouldFetchRides = id.startsWith('AT');
    const shouldFetchBookings = !id.startsWith('AT');


    //Bookings
    const {  data:booking, isPending:bookingIsPending, isError:bookingIsError } = useQuery({
        queryKey:['seatBookings'],
        queryFn: () => fetchBookings({id}),
        enabled: shouldFetchBookings,
    });


    //Rides
    const {  data, isPending, isError } = useQuery({
        queryKey:['Rides'],
        queryFn: () => fetchRides({id}),
        enabled:shouldFetchRides
    });

    function handleSelectType(type){
        setSelectedType(type);
    }

    function handleViewDetails(id){
        setExpanded((prevId) => {
            if(prevId === id){
                return null;
            }
            return id;
        })
    }

    const bookings = (id.startsWith('AT') ? data?.data : booking?.data) || [];
    const filteredBookings = {
        active : bookings.filter((booking) => booking.status === 'Active'),
        completed : bookings.filter((booking) => booking.status === 'Completed')
    }

    const displayedBookings = filteredBookings[selectedType];

    
    return (
        <div className={styles.container}>
        <Slidebar page="Book"/>
        
       <div id ={styles.bookings}>
        <BookingTabs
            booking={filteredBookings}
            onSelectedType = {handleSelectType}
            selectedType = {selectedType}
        >
            {displayedBookings && displayedBookings.length > 0 ? (
            <ol className={styles["booking-items"]}>
                {displayedBookings.map((booking) => (

                <BookingItem
                    key = {booking.booking_id}
                    booking={booking}
                    onViewDetails = {() => handleViewDetails(booking.booking_id)}
                    isExpanded = {expanded === booking.booking_id}
                />
                ))}
            </ol>
            ) : (
                <p>No bookings available</p>
            )}

        </BookingTabs>
       </div>
       </div>
    )
}