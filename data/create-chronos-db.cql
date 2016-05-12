/*
Schema for storing raw air line data.
More can be read about these weather sets here: 
*/
DROP KEYSPACE IF EXISTS air_line_data;
CREATE KEYSPACE air_line_data WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

/*
*Weather stations that correspond with collected raw weather data.
*/
CREATE TABLE airline_record (
    id text PRIMARY KEY,  // Composite of Air Force Datsav3 station number and NCDC WBAN number
    year int,            // Name of reporting station
    month int,    // 2 letter ISO Country ID
    dayofmonth int,      // 2 letter state code for US stations
    dayofweek int,       // International station call sign
    deptime int            // Latitude in decimal degrees
);