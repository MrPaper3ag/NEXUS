# NEXUS

**NEXUS** is an Android dynamic-island experience built on the Dynamic Island Android project by Akira9021, with a custom visual identity and an evolving overlay experience.

> The original app menu is intentionally kept intact. NEXUS focuses its changes on the island/overlay, its visual treatment, and media and notification behaviour.

## Current build

- **App name:** NEXUS
- **Application ID:** `com.nexus.island`
- **Version:** `0.2.0`
- **Build type:** Android debug APK
- **Java:** 17 (Temurin)
- **Build system:** Gradle / GitHub Actions

## What's changed

### NEXUS identity and visuals
- Added a NEXUS-branded home panel.
- Introduced a cyber/neon colour direction with cyan and violet accents.
- Added a stronger frosted-glass treatment to the island surface, including more body tint, gradient depth, and brighter edge highlights.
- Kept the existing app menu and underlying project structure as the foundation.

### Notifications
- Added an in-app message-notice archive that retains recent message notices (up to eight) instead of relying only on the short-lived notice banner.
- Message notices are represented as non-transient island activities so they can remain accessible after the initial notification presentation.
- Notices are removed from the archive when their notification key is forgotten by the message service.

### Media controls
- Improved play/pause handling for media sessions that expose incomplete transport-action flags.
- Added media-button-event fallback handling when the normal transport action is unavailable.
- This improves compatibility, but background playback is ultimately controlled by the individual music app and Android's media-session/background policies.

## Build it

The repository uses GitHub Actions to build against the upstream Android foundation.

1. Open the **Actions** tab in this repository.
2. Select **NEXUS Android build**.
3. Run the workflow, or push a commit to `main`.
4. When the run succeeds, open its artifacts and download **NEXUS-cyber-glass-debug**.

The workflow checks out the upstream project, applies the NEXUS overlay patch, configures Temurin Java 17 and Gradle, builds `assembleDebug`, and uploads the debug APK.

### GitHub Actions versions

The workflow has been upgraded to:
- `actions/checkout@v5`
- `actions/setup-java@v5`
- `gradle/actions/setup-gradle@v5`
- `actions/upload-artifact@v5`

These newer action versions are intended to avoid the Node.js 20 deprecation warnings from the previous workflow.

## Project layout

```
.github/workflows/android.yml   # Cloud Android build workflow
nexus-overlay/
  apply.py                      # Applies NEXUS changes to upstream source
  NexusBrandPanel.kt            # NEXUS-branded home panel
  Theme.kt                      # NEXUS visual theme
```

The workflow applies the overlay to a fresh checkout of the upstream project; the upstream source is not copied wholesale into this repository.

## Current limitations / next improvements

- The glass treatment is a tinted, layered frosted-glass effect; it is not true live backdrop blur on every Android device.
- Notification retention currently covers recent message notices. Further work can improve explicit archive browsing and dismissal behaviour.
- Media-session fallback cannot force a third-party player to keep playing if that player or Android stops its background service.
- The media preview's immediate appearance when leaving a player app still needs further device testing and refinement.

## Credits

- Android foundation: [Akira9021/Dynamic-Island-](https://github.com/Akira9021/Dynamic-Island-)
- NEXUS customisation and build integration: [MrPaper3ag/NEXUS](https://github.com/MrPaper3ag/NEXUS)

---

## Changelog

### 0.2.0 — NEXUS overlay upgrade
- Added NEXUS branding and a cyber/neon visual direction.
- Strengthened the island's layered glass styling.
- Added retention for recent message notifications.
- Improved media play/pause compatibility with media-button fallback.
- Kept the original app menu as-is.

### CI maintenance
- Migrated GitHub Actions dependencies from v4 to v5 to address Node.js 20 deprecation notices.
