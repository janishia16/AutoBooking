import { useEffect, useRef } from 'react';
import { createPortal } from 'react-dom';
import style from './Modal.module.css';
import { useNavigate ,Link} from "react-router-dom";

export default function Modal({ children,onClose }) {
  const dialog = useRef();
  const navigate = useNavigate();

  useEffect(() => {
    const modal = dialog.current;
    modal.showModal();

    return () => {
      modal.close(); // needed to avoid error being thrown
    };
  }, []);


  return createPortal(
    <dialog className={style.modal} ref={dialog}>
      {children}
      <button 
        onClick={() => onClose()} 
        className={style.closeButton}
      >
        ✖
      </button>
    </dialog>,
    document.getElementById('modal')
  );
}
