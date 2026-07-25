import { useState } from "react";
import styles from "./SearchBar.module.css";

import {
    TextField,
    Button,
    MenuItem,
    IconButton
} from "@mui/material";

import SwapHorizIcon from "@mui/icons-material/SwapHoriz";
import FlightTakeoffIcon from "@mui/icons-material/FlightTakeoff";

function SearchBar() {

    const [search, setSearch] = useState({
        from: "",
        to: "",
        date: "",
        passengers: 1,
        travelClass: "Economy",
    });

    const handleChange = (e) => {
        setSearch({
            ...search,
            [e.target.name]: e.target.value,
        });
    };

    const swapCities = () => {
        setSearch({
            ...search,
            from: search.to,
            to: search.from,
        });
    };

    const handleSearch = () => {
        console.log(search);

        // Later we'll call Spring Boot API here
    };

    return (
        <div className={styles.searchBox}>

            <h2>Search Flights</h2>

            <TextField
                label="From"
                name="from"
                value={search.from}
                onChange={handleChange}
                fullWidth
            />

            <div className={styles.swapContainer}>

                <TextField
                    label="To"
                    name="to"
                    value={search.to}
                    onChange={handleChange}
                    fullWidth
                />

                <IconButton
                    onClick={swapCities}
                    className={styles.swapButton}
                >
                    <SwapHorizIcon />
                </IconButton>

            </div>

            <TextField
                type="date"
                name="date"
                value={search.date}
                onChange={handleChange}
                fullWidth
                InputLabelProps={{
                    shrink: true,
                }}
            />

            <TextField
                type="number"
                label="Passengers"
                name="passengers"
                value={search.passengers}
                onChange={handleChange}
                fullWidth
                inputProps={{
                    min: 1,
                    max: 9,
                }}
            />

            <TextField
                select
                label="Cabin Class"
                name="travelClass"
                value={search.travelClass}
                onChange={handleChange}
                fullWidth
            >
                <MenuItem value="Economy">Economy</MenuItem>
                <MenuItem value="Premium Economy">Premium Economy</MenuItem>
                <MenuItem value="Business">Business</MenuItem>
                <MenuItem value="First">First</MenuItem>
            </TextField>

            <Button
                variant="contained"
                startIcon={<FlightTakeoffIcon />}
                fullWidth
                className={styles.searchButton}
                onClick={handleSearch}
            >
                Search Flights
            </Button>

        </div>
    );
}

export default SearchBar;