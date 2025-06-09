import React from 'react';
import { motion } from 'framer-motion';
import styles from './AboutUs.module.css'; // Ensure styling exists
import { Slidebar } from '../UI/Slidebar';

import autoImg from '../Image/Aboutus1.png';

export function AboutUs() {
    return (
        <div className={styles.img}>
            <Slidebar page="AboutUs"/>
            <motion.div 
                className={styles.container}
                initial={{ opacity: 0, y: 50 }} // Fade in from below
                animate={{ opacity: 1, y: 0 }} // Smooth entrance
                transition={{ duration: 0.8 }} // Timing effect
            >
                <h1 className={styles.title}>
                    About Us
                </h1>

                {/* <motion.img 
                    src={autoImg}
                    alt="Auto rickshaw ready to go"
                    className={styles.autoImage} // Add styling in CSS
                    initial={{ opacity: 0, scale: 0.8 }}
                    whileInView={{ opacity: 1, scale: 1 }}
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 0.5 }}
                /> */}
                
                <motion.section 
                    className={styles.section}
                    initial={{ opacity: 0, x: -50 }} // Slide-in from left
                    whileInView={{ opacity: 1, x: 0 }} 
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 0.3 }}
                >
                    <p>
                        Welcome to <strong>Book Your Auto</strong>, the most convenient way to book auto seats for your daily commute from 
                        <strong> Sholingaluru to Cognizant CKC Office.</strong> Our service ensures a smooth, hassle-free travel experience by connecting 
                        passengers with reliable auto drivers.
                    </p>
                </motion.section>

                <motion.section
                    className={styles.section}
                    initial={{ opacity: 0, x: 50 }} // Slide-in from right
                    whileInView={{ opacity: 1, x: 0 }} 
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 0.5 }}
                >
                    <h2 className={styles.heading}>What We Do</h2>
                    <ul>
                        <motion.li whileHover={{ scale: 1.1 }}>🚖 <strong>Book auto seats</strong> for your preferred date and time.</motion.li>
                        <motion.li whileHover={{ scale: 1.1 }}>👨‍✈️ <strong>Get assigned a trusted auto driver</strong> for a safe journey.</motion.li>
                        <motion.li whileHover={{ scale: 1.1 }}>🔄 <strong>Update or cancel bookings</strong> as per your schedule.</motion.li>
                    </ul>
                </motion.section>

                <motion.section 
                    className={styles.section}
                    initial={{ opacity: 0, x: -50 }} // Slide-in from left
                    whileInView={{ opacity: 1, x: 0 }} 
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 0.7 }}
                >
                    <h2 className={styles.heading}>Why Choose Us?</h2>
                    <ul>
                        <motion.li whileHover={{ scale: 1.1 }}>✅ <strong>Effortless Booking:</strong> No more last-minute travel stress.</motion.li>
                        <motion.li whileHover={{ scale: 1.1 }}>✅ <strong>Reliable Auto Drivers:</strong> Professional drivers ensure safety.</motion.li>
                        <motion.li whileHover={{ scale: 1.1 }}>✅ <strong>Flexible Scheduling:</strong> Easily update or cancel your rides.</motion.li>
                        <motion.li whileHover={{ scale: 1.1 }}>✅ <strong>Perfect for Cognizant Employees:</strong> Tailored for your daily commute.</motion.li>
                    </ul>
                </motion.section>

                <motion.section 
                    className={styles.section}
                    initial={{ opacity: 0, scale: 0.8 }} // Fade & scale effect
                    whileInView={{ opacity: 1, scale: 1 }} 
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 0.9 }}
                >
                    <h2 className={styles.heading}>Our Mission</h2>
                    <p>
                        We aim to make daily commutes <strong>stress-free and efficient</strong>, ensuring every passenger gets a
                        <strong> comfortable, safe, and timely ride.</strong>
                    </p>
                </motion.section>

                <motion.section 
                    className={styles.section}
                    initial={{ opacity: 0, x: 50 }} // Slide-in from right
                    whileInView={{ opacity: 1, x: 0 }} 
                    viewport={{ once: true }}
                    transition={{ duration: 0.8, delay: 1.1 }}
                >
                    <h2 className={styles.heading}>Join Us!</h2>
                    <p>
                        Whether you're commuting every day or need a one-time ride, <strong>Book Your Auto</strong> is here to make travel easy.
                        Book your seat today and experience <strong>effortless rides, guaranteed!</strong>
                    </p>
                </motion.section>
            </motion.div>
        </div>
    );
}
