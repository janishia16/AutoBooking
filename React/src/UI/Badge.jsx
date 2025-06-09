import styles from '../Components/static/Bookings.module.css';
import { motion } from 'framer-motion';

export default function Badge({ caption }) {
    return <motion.span 
      animate ={{scale : [1,1.2,1]}}
      transition={{duration :0.3}}
      className={styles["badge"]}>
      {caption}
    </motion.span>
  }
  