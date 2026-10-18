# mapvina-plugins-android: verification 04/10/2026

**Status: SOURCE ANNOTATION BUILD PASS; NAMESPACE COMPATIBILITY BLOCKED.**

Six public plugin artifacts remain `1.0.0`; source build does not republish them.

Source baseline: `01fe2a301f0fa84d4a61a0f0c4ee945bc420eacf` (`origin/main` plus the sample/doc changes in this PR).

Audit only: no SDK API/version changes, tags, publishing or main merge.

## Kiểm thử annotation

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH="$JAVA_HOME/bin:$PATH"
./gradlew :plugin-annotation:assembleDebug --console=plain
```

PASS với JDK 17 (jenv pin JDK 8 không có trên máy audit).
Không coi tất cả plugin hoặc runtime annotation đã PASS từ một module build.
AAR annotation public `1.0.0` vẫn có namespace `com.mapvina.android.plugins`;
RN npm `1.0.2` cần `io.github.mapvina.android.plugins`, nên integration đó BLOCKED.

Android demo dùng plugin legacy và exclude core OpenGL transitive để tránh
duplicate core classes; không suy ra namespace mới đã được phát hành.
Suffix `v9` là tên artifact legacy, không phải version native core hiện tại.
Plugins khác, test app và annotation interactions: NOT TESTED.

## Evidence and acceptance boundary

- [plugins-android.log](evidence/plugins-android.log)

[Cross-repository artifact and runtime audit](https://github.com/mapvina/mapvina-document-android/blob/codex/docs-verification-20261004/docs/verification/2026-10-04/README.md).

PASS applies only to the named checks. BLOCKED is a reproduced failure; NOT TESTED is not a pass.

## Separate follow-up

[Reproduced blocker issue](https://github.com/mapvina/mapvina-react-native/issues/18). SDK/infra fixes are outside this PR.
