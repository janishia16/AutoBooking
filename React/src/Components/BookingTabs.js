import styles from '../Components/static/Bookings.module.css';
import Badge from '../UI/Badge';
import { motion } from 'framer-motion';

function Tab({isSelected,onSelect,badgeCaption,children}){
    return(
        <li>
            <button
                className={isSelected ? styles["selected"] : undefined}
                onClick={onSelect}
            >
                {children}
                <Badge caption={badgeCaption}></Badge>
            </button>
            {isSelected && <motion.div layoutId="tab-indicator" className={styles["active-tab-indicator"]} />}
        </li>
    );
}

export function BookingTabs({
    booking,
    onSelectedType,
    selectedType,
    children
}){
    return(
       <>
        <menu id={styles.tabs}>
            <Tab
                isSelected = {selectedType === 'active'}
                onSelect = {() => onSelectedType('active')}
                badgeCaption = {booking.active.length}
            >
                Active
            </Tab>
            <Tab
                isSelected = {selectedType === 'completed'}
                onSelect = {() => onSelectedType('completed')}
                badgeCaption = {booking.completed.length}
            >
                Completed
            </Tab>
        </menu>
        <div>{children}</div>
       </>
    )
}