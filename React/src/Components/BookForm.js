import { Component, useState } from 'react';

// import ErrorBlock from '../UI/ErrorBlock.jsx';
import styles from './static/BookForm.module.css';
import { useQueryClient } from '@tanstack/react-query';
import formImg from '../Image/Auto2.png'

export  function BookForm({ inputData,onNextPage,action,onSubmit}) {

  
  function handleSubmit(event) {
    event.preventDefault(); //Prevent from Refreshing
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries()); // Convert form data to an object
    if(action !=='update'){
        onNextPage({...data,
        id : id },
        "bookseat"
      );
    }
    else{
      data.no_of_seats = inputData?.no_of_seats ?? '';
      onSubmit({...data,
        id : id }
      );
    }

  }


  const id =localStorage.getItem('Id');
  
  return (
    <div className={styles["form-container"]}>
    <form className={styles["event-form"]} onSubmit={handleSubmit}>
      {!action && (<h1 className={styles.title} >Book Your Seat</h1>)}
      {action && (<h1 className={styles.title} >Update Your Seat</h1>)}
      <p className="control">
        <label htmlFor="title">ID</label>
        <span>{id}</span>
      </p>

      <div className={styles["controls-row"]} >
        <p className="control">
          <label htmlFor="date">Date</label>
          <input
            type="date"
            id="date"
            name="date"
            defaultValue={inputData?.date ?? ''}
            required
          />
        </p>

        <p className="control">
          <label htmlFor="time">Time</label>
          <select id="time" 
                  name="time" 
                  defaultValue={inputData?.time ?? ''}
                  className={styles.dropdown}
                  required
                  
          >
            <option value="08:00">8:00 </option>
            <option value="09:00">9:00 </option>
            <option value="10:00">10:00 </option>
            <option value="11:00">11:00 </option>
            <option value="12:00">12:00 </option>
            <option value="13:00">13:00 </option>
          </select>
        </p>
      
      </div>
      <div className={styles["seat-container"]}>
        <p className="control">
          <label htmlFor="seat">Seat</label>
          <select id="no_of_seats" 
                  name="no_of_seats" 
                  defaultValue={inputData?.no_of_seats ?? ''}
                  className={styles.dropdown}
                  disabled={action === "update"}
                  required
                  
          >
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
            </select>
        </p>
      </div>

      
      <p className={styles["form-actions"]}>
        {!action && <button type="submit" className={styles["submitButton"]}>
                Book
        </button>}
        {action && <button type="submit" className={styles["submitButton"]}>
              Update
        </button>}
      </p>
    </form>
    <img src={formImg} className={styles.imag}/>
    </div>
  );
}

                                       

