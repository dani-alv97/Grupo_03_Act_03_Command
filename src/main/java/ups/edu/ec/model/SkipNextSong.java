package ups.edu.ec.model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * 
 * @author grupo_3
 */
public class SkipNextSong implements ICommand {
    private JTree playlistTree;
    private DefaultMutableTreeNode currentPlaylistNode;
    private DefaultMutableTreeNode currentSongNode;
    private JLabel playingLabel;

    public SkipNextSong(JTree playlistTree, DefaultMutableTreeNode currentPlaylistNode,
                        DefaultMutableTreeNode currentSongNode, JLabel playingLabel) {
        this.playlistTree = playlistTree;
        this.currentPlaylistNode = currentPlaylistNode;
        this.currentSongNode = currentSongNode;
        this.playingLabel = playingLabel;
    }

    @Override
    public void execute() {
        // Obtener el índice de la canción actual en la playlist
        int currentIndex = currentPlaylistNode.getIndex(currentSongNode);

        // Verificar si hay una siguiente canción
        if (currentIndex < currentPlaylistNode.getChildCount() - 1) {
            // Seleccionar la siguiente canción
            currentSongNode = (DefaultMutableTreeNode) currentPlaylistNode.getChildAt(currentIndex + 1);
            String nextSongTitle = currentSongNode.getUserObject().toString();
            Song nextSong = new Song(nextSongTitle, "", "");  // Modificar artista/género según sea necesario

            // Mostrar información de la siguiente canción en la etiqueta
            playingLabel.setText("<html>******************************<br>" +
                     "**   REPRODUCIENDO AHORA    **<br>" +
                     "******************************<br>" +
                     "**   "+ nextSong.getTitle() + "    **<br>" +
                     "******************************</html>");

            // Seleccionar la nueva canción en el árbol
            TreePath path = new TreePath(currentSongNode.getPath());
            playlistTree.setSelectionPath(path);
            playlistTree.scrollPathToVisible(path);
        } else {
            // No hay más canciones, mostrar un mensaje
            playingLabel.setText("Fin de la playlist. No hay más canciones para saltar.");
        }
    }

    // Método para obtener la siguiente canción actualizada (para que la clase principal la pueda usar)
    public DefaultMutableTreeNode getNextSongNode() {
        return currentSongNode;
    }
}
