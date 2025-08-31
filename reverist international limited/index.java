function a(i, e) {
    document.visibilityState === "hidden" ? window.addEventListener("visibilitychange", () => {
        chrome.embeddedSearch.newTabPage.logEventWithTimestamp(i, performance.now(), !0)
    }
    , {
        once: !0
    }) : chrome.embeddedSearch.newTabPage.logEventWithTimestamp(i, e, !1)
}
var n = new PerformanceObserver(i => {
    for (let e of i.getEntries())
        if (e.name === "quicklinks-paint")
            a(11, e.startTime),
            performance.measure("most-visited-ttvr", "navigationStart", e.name);
        else if (e.name === "shell-interactive")
            a(62, e.startTime),
            a(63, e.startTime),
            performance.measure("shell-interactive", "navigationStart", e.name);
        else if (e.name === "image-paint" && e.identifier === "wallpaper")
            a(60, e.startTime),
            performance.measure("background-image-ttvr", {
                start: "navigationStart",
                end: e.startTime
            });
        else if (e.name === "quicklinks-clicked") {
            let t = "OTHER"
              , r = e.detail;
            switch (r.topsiteSource) {
            case 0:
                {
                    t = "MOST_VISITED";
                    break
                }
            case 3:
                {
                    t = "CUSTOM_LINK";
                    break
                }
            case 7:
                {
                    t = "NEXT_SITES";
                    break
                }
            case 8:
                {
                    t = "PROMOTED_MEDIA_SITES";
                    break
                }
            default:
                break
            }
            chrome.ntpSettingsPrivate.logTopSitesTileActivated(t, r.position, r.slotIndexByTileSource, r.url)
        }
}
);
n.observe({
    type: "mark",
    buffered: !0
});
n.observe({
    type: "element",
    buffered: !0
});

