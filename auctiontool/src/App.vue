<template>
  <div id="app">
    <PlayerBid></PlayerBid>
    <SplTeams></SplTeams>
  </div>
</template>

<script>
import PlayerBid from "./components/PlayerBid";
import SplTeams from './components/SplTeams'

export default {
  name: 'app',
  components: {
    PlayerBid,
    SplTeams
  }
}
</script>

<style>

</style>
