#!/bin/bash
# usage: tools/server.sh start [with-mod|no-mod] | stop   (scratch NeoForge 21.1.250 + Create 6.0.10 server in run/server-real; log run/server-real/console.log)
cd "$(dirname "$0")/.."
S=run/server-real
J=$(ls -d ~/.gradle/jdks/*-21-*/jdk-21*/Contents/Home | head -1)/bin/java
case "$1" in
  start)
    rm -f $S/mods/noblanklinks-*.jar
    [ "$2" = "with-mod" ] && cp build/libs/noblanklinks-$(sed -n 's/^mod_version=//p' gradle.properties).jar $S/mods/
    rm -rf $S/world
    cd $S
    rm -f console.log
    nohup "$J" @user_jvm_args.txt @libraries/net/neoforged/neoforge/21.1.250/unix_args.txt nogui > console.log 2>&1 < /dev/null &
    echo $! > server.pid
    cd ../..
    for i in $(seq 1 120); do grep -q 'RCON running' $S/console.log && break; kill -0 $(cat $S/server.pid) 2>/dev/null || break; sleep 2; done
    grep -E 'Done \(|Mixing|noblanklinks' $S/console.log | head -5
    ;;
  stop)
    [ -f $S/server.pid ] || exit 0
    printf 'stop\n' | python3 tools/rcon.py > /dev/null 2>&1
    for i in $(seq 1 60); do kill -0 $(cat $S/server.pid) 2>/dev/null || break; sleep 2; done
    kill $(cat $S/server.pid) 2>/dev/null; rm -f $S/server.pid; echo stopped
    ;;
esac
