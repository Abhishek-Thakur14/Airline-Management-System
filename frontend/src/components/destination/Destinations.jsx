import styles from "./Destinations.module.css";
import DestinationCard from "./DestinationCard";

import delhi from "../../assets/images/delhi.jpg";
import mumbai from "../../assets/images/mumbai.jpg";
import goa from "../../assets/images/goa.jpg";
import bangalore from "../../assets/images/bangalore.jpg";

const destinations = [
    {
        city: "Delhi",
        image: delhi,
        price: "3,999",
    },
    {
        city: "Mumbai",
        image: mumbai,
        price: "4,299",
    },
    {
        city: "Goa",
        image: goa,
        price: "2,899",
    },
    {
        city: "Bangalore",
        image: bangalore,
        price: "4,499",
    },
];

function Destinations() {
    return (
        <section className={styles.destinations}>
            <h2>Popular Destinations</h2>

            <p>
                Discover our most loved destinations with the best airfare deals.
            </p>

            <div className={styles.grid}>
                {destinations.map((destination) => (
                    <DestinationCard
                        key={destination.city}
                        city={destination.city}
                        image={destination.image}
                        price={destination.price}
                    />
                ))}
            </div>
        </section>
    );
}

export default Destinations;