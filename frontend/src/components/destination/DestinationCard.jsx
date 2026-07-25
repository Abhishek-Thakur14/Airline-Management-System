import styles from "./DestinationCard.module.css";

import LocationOnIcon from "@mui/icons-material/LocationOn";
import FlightTakeoffIcon from "@mui/icons-material/FlightTakeoff";

function DestinationCard({ city, image, price }) {
    return (
        <div className={styles.card}>
            <div className={styles.imageWrapper}>
                <img src={image} alt={city} />

                <div className={styles.overlay}>
                    <LocationOnIcon />
                    <span>{city}</span>
                </div>
            </div>

            <div className={styles.content}>
                <p>Starting From</p>

                <h3>₹ {price}</h3>

                <button>
                    <FlightTakeoffIcon />
                    Book Now
                </button>
            </div>
        </div>
    );
}

export default DestinationCard;