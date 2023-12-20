<template>
    <div class="player-bid">
        <b-button size="lg" variant="outline-dark" @click="getRandomPlayerForAuction">Get next player for auction</b-button>
        <b-modal id="player_bid" v-if="randomPlayer" hide-footer ref="player-bid-modal" centered
                 size="lg" v-model="canShowModal" title="The player for auction is..."
                 header-bg-variant="dark" header-text-variant="light">
            <div class="modal-outer-panel">
                <div class="d-block text-center fullname">
                    <h1>{{randomPlayer.fullName}}</h1>
                </div>
                <b-media>
                    <div class="player-profile-picture">
                        <img :src="require('../assets/players/' + randomPlayer.profilePicture)">
                    </div>
                    <div class="player-profile-info">
                        <h4>Strengths</h4>
                        <div v-if="randomPlayer.strengths" style="width: 325px;">
                            <span v-for="sport in randomPlayer.strengths" v-bind:key="sport">
                                <SportIcon v-bind:sport-name="sport"></SportIcon>
                            </span>
                        </div>
                        <br/>
                        <h6>Participating in SPL since {{randomPlayer.splDebut}}</h6>
                        <br/>
                        <ul v-if="randomPlayer.achievements" class="list-unstyled">
                            <b-media tag="li" v-for="achievement in randomPlayer.achievements" v-bind:key="achievement">
                                <b-img slot="aside" width="30" height="30" :src="require('../assets/icons/trophy.jpg')"></b-img>
                                <span>{{achievement}}</span>
                            </b-media>
                        </ul>
                    </div>
                </b-media>
                <br/>
                <div class="team-assignment-panel">
                    <h5>Chosen for team</h5>
                    <a v-for="team in teams" v-bind:key="team.name" @click="assignTeam(team)"
                       class="team-selection" :class="{ 'team-selected': playerAssignment.teamName == team.name }">
                        <figure class="team-with-caption">
                            <img :src="require('../assets/teams/' + team.teamLogo)" :alt="team.name" style="width: 100px; height: 100px;">
                            <figcaption class="team-name-caption">{{team.name}}<br/>Max Bid: {{team.maximumBidForNextPlayer}}</figcaption>
                        </figure>
                    </a>
                    <br/><br/>
                    <b-container fluid>
                        <b-row>
                            <b-col sm="6">
                                <h5 class="acquired-points">Acquired Points</h5>
                            </b-col>
                            <b-col sm="6">
                                <b-form-input id="input-large" type="number" size="lg" placeholder="Points" v-model="playerAssignment.acquiredPoints"></b-form-input>
                            </b-col>
                        </b-row>
                    </b-container>
                </div>
                <br/>
                <b-button block size="lg" variant="dark" @click="submitPlayerAssignment">Submit</b-button>
            </div>
        </b-modal>
    </div>
</template>

<script>
    import SportIcon from "../components/SportIcon";
    import {IS_DEBUG, RESOURCE_PLAYER_ASSIGNMENTS, RESOURCE_RANDOM_PLAYER, RESOURCE_TEAMS} from "../constants/constants";

    export default {
        name: "PlayerBid",
        components: {SportIcon},
        data () {
            return {
                randomPlayer: null,
                canShowModal: false,
                profilePictureProps: { width: 350, height: 350 },
                playerAssignment: {
                    playerAlias: null,
                    teamName: null,
                    role: "Player",
                    acquiredPoints: 25
                },
                teams: null
            }
        },
        methods: {
            getRandomPlayerForAuction() {
                this.playerAssignment =  {
                    playerAlias: null,
                    teamName: null,
                    role: "Player",
                    acquiredPoints: 25
                };
                this.getAllTeams();
                this.$http.get(RESOURCE_RANDOM_PLAYER).then(result => {
                        console.log(result);
                    this.randomPlayer = result.body;
                    this.playerAssignment.playerAlias = this.randomPlayer.alias;
                    this.canShowModal = true;
                }, error => {
                    /* eslint-disable */
                    console.error(error);
                });
            },
            getAllTeams() {
                this.$http.get(RESOURCE_TEAMS).then(result => {
                    if (IS_DEBUG) {
                        /* eslint-disable */
                        console.log(result);
                    }
                    this.teams = result.body;
                }, error => {
                    /* eslint-disable */
                    console.error(error);
                });
            },
            assignTeam(team) {
                this.playerAssignment.teamName = team.name;
                /* eslint-disable */
                console.log(this.playerAssignment);
            },
            submitPlayerAssignment() {
                this.$http.post(RESOURCE_PLAYER_ASSIGNMENTS, this.playerAssignment).then(result => {
                    this.canShowModal = false;
                    window.location.reload();
                    if (IS_DEBUG) {
                        /* eslint-disable */
                        console.log(result);
                    }
                }, error => {
                    /* eslint-disable */
                    console.error(error);
                });
            }
        }
    }
</script>

<style scoped>
    .fullname {
        margin-bottom: 30px;
    }

    .player-bid {
        margin: 50px 0 35px;
    }

    .player-profile {
        padding: 20px;
    }

    .player-profile-picture {
        width: 350px;
        height: 350px;
        display: inline-block;
        vertical-align: top;
    }

    .player-profile-picture img {
        width: 350px;
        height: 350px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .player-profile-info {
        display: inline-block;
        padding: 10px 10px 10px 25px;
        vertical-align: top;
    }

    .player-profile-info h4 {
        margin-bottom: 10px;
    }

    .btn.btn-outline-dark.btn-lg,
    .btn.btn-dark.btn-lg {
        border-radius: 50rem !important;
        margin: 0 auto;
        display: block;
    }

    .modal-body {
        padding: 2rem;
    }

    .team-name-caption {
        width: 100px;
        display: table-caption;
        caption-side: bottom;
        padding-top: 5px;
        text-align: center;
    }

    .team-with-caption {
        display: inline-block;
        margin: 10px;
        border: 3px solid transparent;
    }

    .team-selection {
        cursor: pointer;
    }

    .team-selection:hover .team-with-caption,
    .team-selected .team-with-caption {
        border: 3px solid #aaa;
    }

    .acquired-points {
        line-height: 48px;
    }

    .team-assignment-panel {
        padding: 10px 90px;
    }

    .modal-outer-panel {
        padding: 15px;
    }
</style>