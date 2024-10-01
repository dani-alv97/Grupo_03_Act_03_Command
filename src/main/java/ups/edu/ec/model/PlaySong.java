package ups.edu.ec.model;

import javax.swing.*;

/**
 * 
 * @author grupo_3
 */
public class PlaySong implements ICommand {
    private Song song;
    private JLabel playingLabel;

    public PlaySong(Song song, JLabel playingLabel) {
        this.song = song;
        this.playingLabel = playingLabel;
    }

    @Override
    public void execute() {
        // Actualizar la etiqueta de reproducción con la información de la canción
        playingLabel.setText("<html>******************************<br>" +
                     "**   REPRODUCIENDO AHORA    **<br>" +
                     "******************************<br>" +
                     "**   " + song.getTitle() + "    **<br>" +
                     "******************************</html>");
    }
}
