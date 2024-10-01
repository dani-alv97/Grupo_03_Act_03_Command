package ups.edu.ec.model;

import javax.swing.*;

/**
 * 
 * @author grupo_3
 */
public class StopSong implements ICommand {
    private JLabel playingLabel;

    public StopSong(JLabel playingLabel) {
        this.playingLabel = playingLabel;
    }

    @Override
    public void execute() {
        // Actualizar la etiqueta de reproducción para indicar que la canción se ha detenido
        playingLabel.setText("Canción detenida");
    }
}
