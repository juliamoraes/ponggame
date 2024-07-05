package utils;

public enum global {

    Title("Pong Game"),
    HomeScreen("HOME SCREEN"),
    TextStyle("Consolas"),

    // ------------ This Global Enum use for LeaderBoards -------------
    LeaderBoard("Leaderboard"),
    LeaderBoardFontStyle("Arial"),

    HomeBtnTitle("Back to views.Home"),

    // --------------- This Enum is use for File Handling ---------------------
    FileError("Error: File not found"),
    ErrorTitle("Error"),
    FileName("userinfo.txt"),

    // --------------- This Enum is use for Info Panel -----------------------
    StartBtnTitle("Start Game"),

    SetNameLabel("Name"),
    SetAgeLabel("Age"),

    ShowMessageBoxText("Please enter your name and age."),


    // --------------- This Enum is use for views.Home -----------------------
    ShowTitlePong("PONG"),

    // --------------- This Enum is use for Game Frame -----------------------
    ExitBtnTitle("Exit"),

    // --------------- This Enum is use for Game Panel -----------------------
    PlayerOneTitle("You"),
    PlayerTwoTitle("Artificial Intelligence"),

    QuitGameTitle("Quit Game"),
    RestartGameTitle("Restart Game"),

    GameOverText("Game Over"),

    WinnerText("Wins!"),

    Player1Score("Your filehandling.Score"),
    Player2Score("Artificial Intelligence filehandling.Score");



    // Constant Private variable which exists only in this class
    private final String title;

    // --------------------------------------------------------
    //  ---------- Create the Enum Class Constructors ---------
    // --------------------------------------------------------
    global(String title) {
        this.title = title;
    }

    // ------- Getting the Value which we have to set in Enums ----------
    public String getValue() {
        return title;
    }
}
