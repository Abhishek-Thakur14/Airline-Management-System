import styles from "./Hero.module.css";
import SearchBar from "../Search/SearchBar";

import heroPlane from "../../assets/images/hero-plane.png";

function Hero() {
    return (
        <section className={styles.hero}>
            <div className={styles.overlay}></div>

            <div className={styles.container}>
                {/* LEFT */}

                <div className={styles.left}>
          <span className={styles.badge}>
            ✈ Welcome to SkyBook Airlines
          </span>

                    <h1>
                        Discover Your
                        <br />
                        Next Journey
                    </h1>

                    <p>
                        Fly smarter with SkyBook. Compare fares, book flights,
                        and travel with confidence to destinations across India
                        and around the world.
                    </p>

                    <div className={styles.buttons}>
                        <button className={styles.primary}>
                            Explore Flights
                        </button>

                        <button className={styles.secondary}>
                            Learn More
                        </button>
                    </div>
                </div>

                {/* RIGHT */}

                <div className={styles.right}>
                    <img
                        src={heroPlane}
                        alt="Airplane"
                        className={styles.plane}
                    />

                    <div className={styles.searchCard}>
                        <SearchBar />
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Hero;