# Create: No Blank Links

**Create Redstone Links with a blank frequency never carry power. One tiny server-side mixin.**

## What it does

A Redstone Link with **both frequency slots empty** is on the blank channel. In Create that channel is one global network: every powered blank transmitter within link range feeds every blank receiver on the server, whoever placed it.

With this mod the blank channel is dead:

- a blank receiver always reads 0, whatever blank transmitters are around;
- links with **at least one** frequency item work exactly as before.

## Why

[Create: Propulsion](https://modrinth.com/mod/create-propulsion) 1.1.5 vector thrusters carry four blank-frequency link receivers and do not refresh their own power after joining the network. One forgotten powered blank Redstone Link anywhere within link range tilts the last-placed thruster. In general, a stray blank transmitter leaks into every blank receiver on the server.

## Behaviour change

**Blank links stop working.** Any build that relies on two Redstone Links with no frequency item goes dark. Put the same item in a slot of both links (an apple, a dirt block, anything) and it works again. Nothing is saved differently; removing the mod brings the old behaviour back.

## Install

1. NeoForge 1.21.1 and Create 6.0.x in `mods/`.
2. Drop the jar into `mods/`.
3. **Server only is enough.** Clients may install it or not. In singleplayer, install it on the client.

## Requirements

| Mod | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1+ (tested 21.1.250) |
| [Create](https://modrinth.com/mod/create) | 6.0.7+, below 6.1.0 (tested 6.0.10) |
| Java | 21 |

No blocks, items, config, packets or save-format changes. One mixin on Create's `RedstoneLinkNetworkHandler`.

## Links

- [Source](https://github.com/SenDeliver/create-no-blank-links)
- [Issues](https://github.com/SenDeliver/create-no-blank-links/issues)
- [Changelog](https://github.com/SenDeliver/create-no-blank-links/blob/main/CHANGELOG.md)

MIT License. Built on Create (not bundled). Developed with AI assistance (Claude); design, testing and direction by SenDeliver.
