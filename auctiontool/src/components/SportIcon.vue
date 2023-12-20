<template>
    <span>
        {{determineSportCategory(sportName)}}
        <figure class="sport-icon-with-caption">
            <img :src="require('../assets/icons/' + iconPath)" :alt="sportLabel" style="width: 50px; height: 50px;">
            <figcaption class="image-caption">{{sportLabel}}</figcaption>
        </figure>
    </span>
</template>

<script>
    export default {
        name: "SportIcon",
        props: ['sportName'],
        data () {
            return {
                iconPath: null,
                sportLabel: null
            }
        },
        mounted() {
        },
        methods: {
            determineSportCategory(sportName) {
                switch (sportName) {
                    case "Carrom":
                    case "Carrom ":
                    case "Carom":
                    case "carrom":
                    case "carom":
                        this.sportLabel = "Carrom";
                        this.iconPath = "carrom.png";
                        break;
                    case "TT":
                    case "TableTennis":
                    case "Table Tennis":
                    case "Tabletennis":
                    case "Table tennis":
                        this.sportLabel = "TT";
                        this.iconPath = "tt_icon.png";
                        break;
                    case "Chess":
                    case "chess":
                        this.sportLabel = "Chess";
                        this.iconPath = "chess-icon.png";
                        break;
                    case "Badminton":
                        this.sportLabel = "Badminton";
                        this.iconPath = "badminton_cxldt1.png";
                        break;
                    case "Cricket":
                    case "cricket":
                        this.sportLabel = "Cricket";
                        this.iconPath = "cricket-vector-wicket-2.png";
                        break;
                    case "Football":
                    case "football":
                    case "Foot ball":
                    case "foot ball":
                        this.sportLabel = "Football";
                        this.iconPath = "nicubunu_Soccer_ball.png";
                        break;
                    case "Volleyball":
                    case "Volley ball":
                    case "volleyball":
                    case "volley ball":
                        this.sportLabel = "Volleyball";
                        this.iconPath = "Sports-Volleyball-icon.png";
                        break;
                    case "Swimming":
                        this.sportLabel = "Swimming";
                        this.iconPath = "swimming.png";
                        break;
                    case "Throwball":
                    case "Throw ball":
                    case "Throw Ball":
                    case "throw ball":
                    case "throwball":
                        this.sportLabel = "Throwball";
                        this.iconPath = "throwball.png";
                        break;
                    case "Video games":
                    case "Videogame":
                    case "video game":
                    case "video games":
                    case "VideoGame":
                        this.sportLabel = "Video Games";
                        this.iconPath = "video_game.png";
                        break;
                    default:
                        this.sportLabel = sportName;
                        this.iconPath = "general-sports.jpg";
                }
            }
        }
    }
</script>

<style scoped>
    .image-caption {
        font-size: 12px;
        width: 50px;
        display: table-caption;
        caption-side: bottom;
        padding-top: 3px;
        text-align: center;
    }

    .sport-icon-with-caption {
        display: inline-block;
        margin: 5px;
    }
</style>