import styles from "./Footer.module.css";

function Footer() {
    return (
        <footer className={styles.footer}>
            <h3>SkyBook Airlines</h3>

            <p>
                Book Domestic Flights Across India Easily & Securely.
            </p>

            <p>
                © 2026 SkyBook Airlines. All Rights Reserved.
            </p>
        </footer>
    );
}

export default Footer;