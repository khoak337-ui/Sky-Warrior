package utils;

// Đây là Custom Exception tự định nghĩa (Tiêu chí 4)
public class GameDataException extends Exception {
    public GameDataException(String message) {
        super(message);
    }
}