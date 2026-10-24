# No Blank Links

NeoForge 1.21.1 mixin mod for Create 6.0.x. Redstone Links with no frequency item in either slot (network key `EMPTY/EMPTY`) never carry power. Links with at least one frequency item work as before.

Why: a freshly placed Redstone Link is on the blank channel. Every blank transmitter on the server feeds every blank receiver (within link range), so a forgotten blank link powers other players' contraptions. This mod makes the blank channel dead.

How: one mixin on `RedstoneLinkNetworkHandler`.
- `updateNetworkOf`: the `getTransmittedStrength()` result is replaced by 0 when the actor's network key is blank, so receivers on that key get 0.
- `hasAnyLoadedPower`: returns false for a blank key (Linked Controller display stays consistent).

Server-only install works (`displayTest="IGNORE_ALL_VERSION"`, clients without the mod can join). Depends on `create [6.0.7,6.1.0)`.

## Build

```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home
./gradlew build --no-configuration-cache
```
Jar: `build/libs/noblanklinks-1.0.0.jar`.

## Test (2026-10-24, headless dedicated server NeoForge 21.1.250 + Create 6.0.10, superflat)

`tools/server.sh start [no-mod|with-mod]` installs/starts a scratch server in `run/server-real` (NeoForge installer layout, RCON 25702), `python3 tools/rcon.py < tools/scen-links.txt` runs the scenario, `tools/server.sh stop`.

Layout: transmitter link on a redstone block, receiver link 5 blocks away with a redstone lamp next to it. One pair blank, one pair with an apple in `FrequencyFirst` on both links. Check: `execute if block <lamp> minecraft:redstone_lamp[lit=true]`.

| Case | Without mod | With mod |
|---|---|---|
| Blank pair (no frequency items) | lamp lit, receiver `Receive: 15` | lamp off, receiver `Receive: 0` |
| Apple pair (`FrequencyFirst` apple on both) | lamp lit, `Receive: 15` | lamp lit, `Receive: 15` |

Mixin application confirmed in `logs/debug.log` (`Mixing RedstoneLinkNetworkHandlerMixin from noblanklinks.mixins.json`). Logs: `tools/logs/baseline-nomod.rcon`, `tools/logs/with-mod.rcon`.

Notes: a `setblock` of a receiver link with no NBT leaves its LinkBehaviour uninitialised (no `FrequencyFirst` key, never receives); give it any NBT (`{Transmitter:0b}`). Not tested: Linked Controller in hand on the blank channel, links on Create contraptions.
