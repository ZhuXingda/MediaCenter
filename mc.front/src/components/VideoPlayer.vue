<template>
  <div id="videoElement"></div>
</template>
<script>
import Player from 'xgplayer'
import FlvPlayer from 'xgplayer-flv'
import FlvJs from 'flv.js'
import '../assets/globle.css'

export default {
  name: "VideoPlayer",
  mounted() {
    this.initVideo()
  },
  methods: {
    initVideo() {
/*      let player = new FlvPlayer({
        el:document.querySelector('#videoElement'),
        url: 'http://10.6.11.250:8081/flv/LoveDeathandRobots/S02E02_CN.flv',
        playsinline: true,
        fluid: true,
        volume: 0.5,
        videoInit: true,
        cors: true,
        playbackRate: [0.5, 1, 1.5, 2],
        preloadTime: 15, // 默认预加载 15 秒
        /!*textTrack: [
          {
            src: "http://10.6.11.250:8081/flv/LoveDeathandRobots/Love.Death.and.Robots.S02E02.1080p.WEB.H264-GLHF.chi.ass",
            kind: "subtitles",
            label: "中文字幕",
            srclang: "zh",
            default: true
          }
        ],*!/
      });*/


      let player = new Player({
        el:document.querySelector('#videoElement'),
/*
        url: 'rtmp://10.6.11.250/static/001.mp4',
*/
        url: 'http://10.6.11.250:8081/mp4/GameofThronesS1/S01E01.mp4',
        keyShortcut: 'on',
        playsinline: true,
        fluid: true,
        volume: 0.5,
        videoInit: true,
        playbackRate: [0.5, 1, 1.5, 2],
        preloadTime: 15, // 默认预加载 15 秒
/*        textTrack: [
          {
            src: "http://10.6.11.250:8081/flv/LoveDeathandRobots/Love.Death.and.Robots.S02E02.1080p.WEB.H264-GLHF.chi.vtt",
            kind: "subtitles",
            label: "中文字幕",
            srclang: "zh",
            default: true
          }
        ],*/
      })


    }
  }
}
</script>

<style scoped>
</style>
