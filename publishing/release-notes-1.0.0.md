First release of **Create: No Blank Links**.

Create Redstone Links with **both frequency slots empty** (the blank `EMPTY/EMPTY` channel) never carry power. Links with at least one frequency item work exactly as before.

### Why

- Create: Propulsion 1.1.5 vector thrusters carry four blank-frequency receivers and do not refresh their own power after joining the network, so one forgotten powered blank link anywhere within link range tilts the last-placed thruster.
- In general, every blank transmitter on the server leaks into every blank receiver.

### Notes

- NeoForge 1.21.1, Create 6.0.7 to 6.0.x (tested 6.0.10). Only required dependency: Create.
- **Server-side install is enough.** Clients may install it or not.
- **Behaviour change:** builds that rely on blank-frequency links stop working. Put the same item in a slot of both links.
- No save, config or network changes. Removing the mod restores Create's behaviour.
