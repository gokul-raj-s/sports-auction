<template v-if="teams">
    <div class="teams-panel">
        <b-card-group deck>
            <b-card v-for="team in teams" v-bind:key="team.name"
                    v-bind:title="team.name"
                    v-bind:img-src="require('../assets/teams/' + team.teamLogo)"
                    v-bind:img-alt="team.name"
                    img-top
                    img-height="200"
                    tag="article"
                    class="mb-2">
                <b-container>
                    <b-row>
                        <b-col cols="8">Balance points</b-col>
                        <b-col>{{team.balancePoints}}</b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="8">Total number of players</b-col>
                        <b-col>{{team.numberOfPlayers}}</b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="8">Number of women players</b-col>
                        <b-col>{{team.numberOfWomenPlayers}}</b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="8">Maximum bid for next player</b-col>
                        <b-col>{{team.maximumBidForNextPlayer}}</b-col>
                    </b-row>
                </b-container>
            </b-card>
        </b-card-group>
        <b-card-group deck>
            <b-card no-body v-for="team in teams" v-bind:key="team.name"
                    class="mb-2">
                <b-table small :items="teamAssignments[team.name]" :fields="tableFields">
                    <template slot="profilePicture" slot-scope="data">
                        <b-img v-bind="mainProps" rounded="circle" v-bind:src="require('../assets/players/' + data.value)"></b-img>
                    </template>
                </b-table>
            </b-card>
        </b-card-group>
    </div>
</template>

<script>
    import {IS_DEBUG, RESOURCE_PLAYER_ASSIGNMENTS_BY_TEAM, RESOURCE_TEAMS} from "../constants/constants";

    export default {
        name: "SplTeams",
        data () {
            return {
                teams: null,
                teamAssignments: {
                    Snipers: null,
                    Matadorz: null,
                    Torpedoes: null,
                    Zionz: null
                },
                tableFields: [
                    {
                        key: 'profilePicture',
                        label: ' '
                    },
                    {
                        key: 'fullName',
                        label: 'Player'
                    },
                    {
                        key: 'acquiredPoints',
                        label: 'Points'
                    },
                    {
                        key: 'role',
                        label: 'Role'
                    }
                ],
                mainProps: { width: 30, height: 30 }
            }
        },
        mounted() {
            this.$http.get(RESOURCE_TEAMS).then(result => {
                if (IS_DEBUG) {
                    /* eslint-disable */
                    console.log(result);
                }
                this.teams = result.body;
                this.teams.forEach(team => this.initializeTeamAssignments(team));
            }, error => {
                /* eslint-disable */
                console.error(error);
            });
        },
        methods: {
            initializeTeamAssignments: function (team) {
                this.$http.get(RESOURCE_PLAYER_ASSIGNMENTS_BY_TEAM + team.name).then(result => {
                    if (IS_DEBUG) {
                        /* eslint-disable */
                        console.log(result);
                    }
                    this.teamAssignments[team.name] = result.body;
                    if (IS_DEBUG) {
                        /* eslint-disable */
                        console.log(this.teamAssignments);
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
.teams-panel {
    padding: 20px;
}
</style>