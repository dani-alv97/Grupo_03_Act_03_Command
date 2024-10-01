package ups.edu.ec.controller;

import ups.edu.ec.model.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Map;

/**
 * 
 * @author grupo_3
 */
public class MusicController {
    
    private ReproductorInvoker invoker;

    public MusicController() {
        this.invoker = new ReproductorInvoker();  // Invoker para ejecutar los comandos
    }

    // Método para reproducir una canción
    public void playSong(Song song, JLabel playingLabel) {
        ICommand playSongCommand = new PlaySong(song, playingLabel);
        invoker.setCommand(playSongCommand);
        invoker.execute();
    }

    // Método para detener una canción
    public void stopSong(JLabel playingLabel) {
        ICommand stopSongCommand = new StopSong(playingLabel);
        invoker.setCommand(stopSongCommand);
        invoker.execute();
    }

    // Método para saltar a la siguiente canción
    public void skipNextSong(JTree playlistTree, DefaultMutableTreeNode currentPlaylistNode,
                             DefaultMutableTreeNode currentSongNode, JLabel playingLabel) {
        ICommand skipNextCommand = new SkipNextSong(playlistTree, currentPlaylistNode, currentSongNode, playingLabel);
        invoker.setCommand(skipNextCommand);
        invoker.execute();
    }

    // Método para crear una nueva playlist
    public void createPlaylist(String playlistName, DefaultMutableTreeNode rootNode,
                               DefaultTreeModel treeModel, Map<String, DefaultMutableTreeNode> playlistNodes,
                               JLabel feedbackLabel) {
        ICommand createPlaylistCommand = new CreatePlaylist(playlistName, rootNode, treeModel, playlistNodes, feedbackLabel);
        invoker.setCommand(createPlaylistCommand);
        invoker.execute();
    }

    // Método para agregar una canción a una playlist
    public void addSongToPlaylist(String songTitle, String playlistName, DefaultMutableTreeNode playlistNode,
                                  DefaultTreeModel treeModel, JLabel feedbackLabel) {
        ICommand addSongCommand = new AddSongToPlaylist(songTitle, playlistName, playlistNode, treeModel, feedbackLabel);
        invoker.setCommand(addSongCommand);
        invoker.execute();
    }
}
