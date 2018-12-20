package formater

import javax.swing.JFileChooser
import javax.swing.JOptionPane
import java.nio.file.Path
import java.nio.file.Paths

class Formater {

    static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser()
        fileChooser.setCurrentDirectory(new File("/home/joao/Desktop"))

        int result = fileChooser.showOpenDialog(null)
        String fileName = ""
        String fileContent = ""

        if (result == JFileChooser.APPROVE_OPTION) {
            fileName = fileChooser.getSelectedFile().getPath()
            JOptionPane.showMessageDialog(null, "You selected " + fileName)
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "You selected nothing.")
            return
        } else if (result == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null, "An error occurred.")
            return
        }

        fileContent = getConteudoArquivo(fileName)
        fileContent = UtilsFormaterRegex.removeTagTweetId(fileContent)
        fileContent = UtilsFormaterRegex.removeTagId(fileContent)
        fileContent = UtilsFormaterRegex.removeTagUrl(fileContent)
        fileContent = UtilsFormaterRegex.removeTagDate(fileContent)
        fileContent = UtilsFormaterRegex.removeTagUsers(fileContent)
        fileContent = UtilsFormaterRegex.removeTagHashtag(fileContent)
        fileContent = UtilsFormaterRegex.formateColumns(fileContent)
        fileContent = UtilsFormaterRegex.removeInconsistence(fileContent)
//        fileContent = UtilsFormaterRegex.removeTagText(fileContent)
    }
//
//    static void saveFile(String fileContent, String fileName) {
//        String newFileName = fileName.replace('.csv','Formated.csv')
//        File newFile = new File("directory", newFileName)
//        newFile.createNewFile();
//    }

    static String getConteudoArquivo(final String filename) {
        final Path filePath = Paths.get(filename)
        final Scanner scanner = new Scanner(filePath)
        final StringBuilder fileContent = new StringBuilder()
        while (scanner.hasNext()) {
            fileContent.append(scanner.nextLine())
        }
        return fileContent.toString()
    }
}
