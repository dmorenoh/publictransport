
# Problem

In the fictional city of Verspaetung, public transport is notoriously unreliable. To tackle the problem, the city council has decided to make the public transport timetable and delay information public, opening up opportunities for innovative use cases.
You are given the task of writing a web API to expose the Verspaetung public transport information.
As a side note, the city of Verspaetung has been built on a strict grid - all location information can be assumed to be from a cartesian coordinate system.
### Data
The Verspaetung public transport information is comprised of 4 CSV files:
- `lines.csv` - the public transport lines.
- `stops.csv` - the stops along each line.
- `times.csv` - the time vehicles arrive & depart at each stop. The timetimestamp is in the format of `HH:MM:SS`.
- `delays.csv` - the delays for each line. This data is static and assumed to be valid for any time of day.

### Challenge
Build a web API which provides the following features:
- Find a vehicle for a given time and X & Y coordinates
- Return the vehicle arriving next at a given stop
- Indicate if a given line is currently delayed
Endpoints should be available via port `8081`

# Solution

 1. Fist task is solved by accessing an endpoint like this
*`http://localhost:8081/lines/findByTimeAndStop?time=[time]xCoordinate=[xValue]&yCoordinate=[yValue]`*
e.g.: `http://localhost:8081/lines/findByTimeAndStop?time=10:00:00&xCoordinate=1&yCoordinate=1`
returns `{"lines":[{"name":"M4"}]}`

 2. Second task is solver by accessing an endpoint like this
*`http://localhost:8081/lines/findByStop?stopId=[stopId]`*
e.g.: `http://localhost:8081/lines/findByStop?stopId=3`
returns `{"lines":[{"name":"M4"},{"name":"200"},{"name":"S75"}]}`

 3. Third one is solved with an endpoint like this
 *`http://localhost:8081/lines/{name}/delay`*
e.g.: `http://localhost:8081/lines/M4/delay`
returns `{"delayed":true}`
