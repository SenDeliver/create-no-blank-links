# Contributing

Thanks for helping with Create: No Blank Links. This page explains how to build the mod and how to send changes.

## Before you start

- **Small fix?** Open a pull request.
- **Behaviour change?** Open an issue first. The mod does one thing on purpose: the blank Redstone Link channel carries no power. Anything beyond that needs agreement first.

## Ground rules

- Be kind. The [Code of Conduct](CODE_OF_CONDUCT.md) applies to issues, pull requests and discussions.
- Everything is in English: code, commits, issues.
- One topic per issue or pull request. Unrelated refactors, reformatting or dependency bumps go in their own pull request, or not at all.
- No new required dependencies and no bundled mods or libraries. Create stays the only required dependency.
- Keep it server-side safe: clients without the mod must still be able to join.
- Code made with AI tools is fine, but you must understand it and test it in game before you send it. Untested pull requests are closed.
- Security problems go through [SECURITY.md](SECURITY.md), not a public issue.

## Build and run

You need **JDK 21 or newer**. Gradle uses a Java 21 toolchain.

```sh
./gradlew build        # builds build/libs/noblanklinks-<version>.jar
./gradlew runServer    # dev server
./gradlew runClient    # dev client
```

## Code style

- Java 21. Match the style of the code around your change.
- No unnecessary comments. Write a comment only when it tells the reader something the code can't.
- Mixin injections are prefixed `noblanklinks$`.

## Frozen IDs

- The mod id `noblanklinks` and the mixin config name `noblanklinks.mixins.json` do not change.
- The mod stores nothing in worlds, so there is no save format to keep.

## Testing

Before a pull request:

1. `./gradlew build` passes.
2. You tested the change on a dedicated server with Create 6.0.10: the blank pair stays dark, the apple pair still lights. `tools/server.sh` and `tools/scen-links.txt` do this headless (see README).

## Pull requests

1. Fork the repo and create a branch from `main`.
2. Keep the pull request small and about one thing.
3. Describe **what** changed, **why**, and **how you tested it**.
4. Add a line to `CHANGELOG.md` under `[Unreleased]`.
5. Be ready for review comments.

## Review and merge

- Only the maintainer (@SenDeliver) reviews and merges. `main` accepts pull requests only; squash merge; release tags are made by the maintainer.
- The **Build** check (`./gradlew build` on GitHub Actions) must pass before a pull request is merged.
- Expect a first answer within about a week. Pull requests with no reply to review comments for 30 days may be closed, you can reopen later.
- Accepted changes land in the next release and get a line in `CHANGELOG.md` with your GitHub name.

By contributing, you agree that your work is released under the [MIT License](LICENSE).
