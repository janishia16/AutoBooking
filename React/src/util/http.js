import { QueryClient } from '@tanstack/react-query';

export const queryClient = new QueryClient();

//User data
export async function fetchData({id}) {
  const url = id.trim().startsWith('AT') 
    ? `http://localhost:8080/data/?id=${id}`
    : `http://localhost:8081/data/?id=${id}`;

  const response = await fetch(url);

  if (!response.ok) {
    const error = new Error('An error occurred while fetching the events');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const { data } = await response.json();

  return data;
}


export async function bookSeat(eventData) {
  
  const response = await fetch(`http://localhost:8080/seatbooking/book`, {
    method: 'POST',
    body: JSON.stringify(eventData),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    const error = new Error('An error occurred while creating the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const event  = await response.json();
  console.log(event)

  return event;
}


export async function fetchBookings({ id }) {
  const response = await fetch(`http://localhost:8080/viewbooking/?id=${id}`);

  if (!response.ok) {
    const error = new Error('An error occurred while fetching the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const  Data  = await response.json();
  return Data;
}

export async function deleteBooking({ booking_id }) { 
  const response = await fetch(`http://localhost:8080/seatbooking/?booking_id=${booking_id}`, {
    method: 'DELETE',
  });

  if (!response.ok) {
    const error = new Error('An error occurred while deleting the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  return response.json();
}




export async function updateBooking({ booking_id, event }) {
  console.log(event)
  const response = await fetch(`http://localhost:8080/seatbooking/?booking_id=${booking_id}`, {
    method: 'PATCH',
    body: JSON.stringify(event),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  let responseData;
  try {
    responseData = await response.json(); // Catch JSON parsing errors
  } catch (err) {
    throw new Error("Failed to parse server response");
  }

  if (!response.ok) {
    const error = new Error('An error occurred while updating the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  return responseData;
}




export async function fetchEvent({ id, signal }) {
  const response = await fetch(`http://localhost:3000/events/${id}`, { signal });

  if (!response.ok) {
    const error = new Error('An error occurred while fetching the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const { event } = await response.json();

  return event;
}

//Payment
export async function payment(eventData) {
  
  const response = await fetch(`http://localhost:8081/SA00011/payment/addpayment`, {
    method: 'POST',
    body: JSON.stringify(eventData),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    const error = new Error('An error occurred while creating the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const event  = await response.json();
  console.log(event)

  return event;
}

//Auto Rides
export async function fetchRides({ id }) {
  const response = await fetch(`http://localhost:8080/${id}`);

  if (!response.ok) {
    const error = new Error('An error occurred while fetching the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const  Data  = await response.json();
  return Data;
}

//Update the Profile

export async function updateProfile({ id,event }) {
  console.log(event)
  const url = id.trim().startsWith('AT') 
    ? `http://localhost:8080/updateprofile/${id}`
    : `http://localhost:8081/updateprofile/${id}`;
  const response = await fetch(url, {
    method: 'PUT',
    body: JSON.stringify(event),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    const error = new Error('An error occurred while updating the event');
    error.code = response.status;
    error.info = await response.json();
    throw error;
  }

  const responseText = await response.text();

  return responseText ;
  
}
