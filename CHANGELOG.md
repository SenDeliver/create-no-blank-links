# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project follows [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.0] - 2026-10-24

First release.

### Added

- Redstone Links with both frequency slots empty (blank channel `EMPTY/EMPTY`) never carry power: `updateNetworkOf` treats a blank transmitter as strength 0, `hasAnyLoadedPower` is `false` for the blank key.
- Links with at least one frequency item are untouched.

### Compatibility

- NeoForge 1.21.1, Create 6.0.7 to 6.0.x (tested 6.0.10). Server-side install is enough; clients may install it or not.
- Behaviour change: builds that rely on blank-frequency links stop working. Put any item in both links.
- No save, config or network changes. Removing the mod restores Create's behaviour.

[Unreleased]: https://github.com/SenDeliver/create-no-blank-links/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/SenDeliver/create-no-blank-links/releases/tag/v1.0.0
