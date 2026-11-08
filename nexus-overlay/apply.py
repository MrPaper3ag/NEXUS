from pathlib import Path
import re
def rep(path, old, new, label):
 p=Path(path); s=p.read_text()
 if old not in s: raise SystemExit(label+' anchor missing')
 p.write_text(s.replace(old,new,1))
p=Path('app/src/main/java/com/akira/island/ui/screens/HomeScreen.kt'); s=p.read_text(); old='Page(tr("Динамический остров", "Dynamic Island")) {\n        IslandPreview()'; new='Page("NEXUS") {\n        NexusBrandPanel(open)\n        IslandPreview()'
if old not in s: raise SystemExit('HomeScreen anchor missing')
p.write_text(s.replace(old,new,1))
p=Path('app/build.gradle.kts'); s=p.read_text().replace('applicationId = "com.akira.island"','applicationId = "com.nexus.island"').replace('versionName = "1.6.5"','versionName = "0.2.0"'); p.write_text(s)
p=Path('app/src/main/java/com/akira/island/core/IslandBus.kt'); rep(p,'    val notice = MutableStateFlow<Notice?>(null)\n','    val notice = MutableStateFlow<Notice?>(null)\n    val messageNotices = MutableStateFlow<List<Notice>>(emptyList())\n','IslandBus')
p=Path('app/src/main/java/com/akira/island/island/Act.kt'); rep(p,'data class NoticeA(val n: Notice) : Act("notice", transient = true)','data class NoticeA(val n: Notice) : Act("notice", transient = n.message == null)','NoticeA')
p=Path('app/src/main/java/com/akira/island/service/Messages.kt'); s=p.read_text(); old='        IslandBus.post(\n            Notice(\n';
if old not in s: raise SystemExit('Messages start missing')
s=s.replace(old,'        val persistentNotice = Notice(\n',1); old='                extraMs = if (actions.isEmpty()) 2_000 else 4_500,\n            ),\n        )'; new='                extraMs = if (actions.isEmpty()) 2_000 else 4_500,\n            )\n        IslandBus.messageNotices.value = (IslandBus.messageNotices.value.filterNot { it.message?.key == sbn.key } + persistentNotice).takeLast(8)\n        IslandBus.post(persistentNotice)';
if old not in s: raise SystemExit('Messages end missing')
s=s.replace(old,new,1); old='    fun forget(key: String) {\n        shown.remove(key)'; new='    fun forget(key: String) {\n        IslandBus.messageNotices.value = IslandBus.messageNotices.value.filterNot { it.message?.key == key }\n        shown.remove(key)';
if old not in s: raise SystemExit('Messages forget missing')
p.write_text(s.replace(old,new,1))
p=Path('app/src/main/java/com/akira/island/island/IslandOverlay.kt'); s=p.read_text(); old='    val notice by IslandBus.notice.collectAsState()\n';
if old not in s: raise SystemExit('Overlay notice missing')
s=s.replace(old,old+'    val messageNotices by IslandBus.messageNotices.collectAsState()\n',1); old='        notice?.let { add(Act.NoticeA(it)) }\n';
if old not in s: raise SystemExit('Overlay acts missing')
s=s.replace(old,old+'        messageNotices.asReversed().filter { it.id != notice?.id }.forEach { add(Act.NoticeA(it)) }\n',1); p.write_text(s)
p=Path('app/src/main/java/com/akira/island/island/Common.kt'); s=p.read_text(); old='    val a = opacity.coerceIn(0.3f, 0.95f)\n    val outline = shape.createOutline(size, layoutDirection, this)'; new='    val a = opacity.coerceIn(0.3f, 0.95f)\n    val bodyAlpha = (0.76f + a * 0.20f).coerceIn(0.82f, 0.96f)\n    val outline = shape.createOutline(size, layoutDirection, this)';
if old not in s: raise SystemExit('Glass alpha missing')
s=s.replace(old,new,1); old='        drawRect(base.copy(alpha = a * 0.8f))\n        drawRect(veil)\n        drawRect(shade)'; new='        drawRect(base.copy(alpha = bodyAlpha))\n        drawRect(Brush.verticalGradient(0f to Color.White.copy(alpha = 0.25f), 0.22f to Color.White.copy(alpha = 0.09f), 0.58f to base.copy(alpha = 0.10f), 1f to Color.Black.copy(alpha = 0.38f)))\n        drawRect(shade)';
if old not in s: raise SystemExit('Glass body missing')
s=s.replace(old,new,1); old='        drawOutline(outline, rim, alpha = 0.35f, style = wideRim)\n        drawOutline(outline, rim, style = thinRim)'; new='        drawOutline(outline, rim, alpha = 0.52f, style = wideRim)\n        drawOutline(outline, rim, style = thinRim)\n        drawOutline(outline, Color.White.copy(alpha = 0.22f), style = Stroke(width = 0.8f * dp))';
if old not in s: raise SystemExit('Glass rim missing')
p.write_text(s.replace(old,new,1))
