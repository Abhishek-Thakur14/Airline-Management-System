import { AppBar, Toolbar, Button, Box, Typography } from "@mui/material";
import FlightTakeoffIcon from "@mui/icons-material/FlightTakeoff";
import { Link } from "react-router-dom";
import styles from "./Navbar.module.css";

function Navbar() {
    return (
        <AppBar position="sticky" className={styles.navbar}>
            <Toolbar className={styles.toolbar}>

                <Box className={styles.logoContainer}>
                    <FlightTakeoffIcon className={styles.logoIcon} />
                    <Typography variant="h5" className={styles.logo}>
                        SkyBook
                    </Typography>
                </Box>

                <Box className={styles.menu}>
                    <Button component={Link} to="/" className={styles.link}>
                        Home
                    </Button>

                    <Button component={Link} to="/flights" className={styles.link}>
                        Flights
                    </Button>

                    <Button component={Link} to="/login" className={styles.link}>
                        Login
                    </Button>

                    <Button component={Link} to="/register" className={styles.link}>
                        Register
                    </Button>
                </Box>

            </Toolbar>
        </AppBar>
    );
}

export default Navbar;