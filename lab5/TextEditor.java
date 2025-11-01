package lab5;

import java.util.ArrayList;
import java.util.List;

class TextEditor 
{
    public static List<String> LineMaker(String text, int lineWidth) 
    {
        String[] paragraphs = text.split("\n");
        List<String> lines = new ArrayList<>();
        
        for (String paragraph : paragraphs) 
        {
            if (paragraph.trim().isEmpty()) 
            {
                lines.add("");
                continue;
            }
            
            String normalizedParagraph = paragraph.trim().replaceAll("\\s+", " ");
            boolean hasRedLine = paragraph.matches("^\\s{4,}.*");
            String[] words = normalizedParagraph.split(" ");
            
            if (hasRedLine) 
            {
                String[] newWords = new String[words.length + 1];
                newWords[0] = "    ";
                System.arraycopy(words, 0, newWords, 1, words.length);
                words = newWords;
            }
            
            List<String> currentLine = new ArrayList<>();
            int currentLength = 0;
            
            for (String word : words) 
            {
                int potentialLength = currentLength + (currentLine.isEmpty() ? 0 : 1) + word.length();
                
                if (potentialLength <= lineWidth) 
                {
                    currentLine.add(word);
                    currentLength = potentialLength;
                } 
                else 
                {
                    if (!currentLine.isEmpty()) 
                    {
                        lines.add(String.join(" ", currentLine));
                        currentLine.clear();
                        currentLength = 0;
                    }
                    
                    if (!word.equals("    ") && word.length() > lineWidth) 
                    {
                        while (word.length() > lineWidth) 
                        {
                            if (lineWidth <= 1) 
                            {
                                break;
                            }
                            lines.add(word.substring(0, lineWidth - 1) + "-");
                            word = word.substring(lineWidth - 1);
                        }
                    }
                    
                    if (!word.isEmpty()) 
                    {
                        currentLine.add(word);
                        currentLength = word.length();
                    }
                }
            }
            
            if (!currentLine.isEmpty()) 
            {
                lines.add(String.join("", currentLine));
            }
        }
        
        return lines;
    }

    public static List<String> EditText(String text, int lineWidth) 
    {
        List<String> lines = LineMaker(text, lineWidth);
        List<String> justifiedLines = new ArrayList<>();
        
        for (int i = 0; i < lines.size(); i++) 
        {
            String line = lines.get(i);
            
            if (line.isEmpty() || line.endsWith("-")) 
            {
                justifiedLines.add(line);
            } 
            else if (i < lines.size() - 1 && !lines.get(i + 1).isEmpty() && !lines.get(i + 1).equals("")) 
            {
                justifiedLines.add(EditLine(line, lineWidth));
            } 
            else 
            {
                justifiedLines.add(line);
            }
        }
        return justifiedLines;
    }

    private static String EditLine(String line, int lineWidth) 
    {
        if (line.startsWith("    ")) 
        {
            String content = line.substring(4);
            String[] words = content.split(" ");
            if (words.length <= 1) 
            {
                return line;
            }
            
            int contentLength = content.replace(" ", "").length();
            int totalSpacesNeeded = lineWidth - contentLength - 4;
            return justifyContent(words, totalSpacesNeeded, true);
        } 
        else 
        {
            String[] words = line.split(" ");
            if (words.length <= 1) 
            {
                return line;
            }
            
            int totalSpacesNeeded = lineWidth - line.replace(" ", "").length();
            return justifyContent(words, totalSpacesNeeded, false);
        }
    }
    
    private static String justifyContent(String[] words, int totalSpacesNeeded, boolean hasRedLine) 
    {
        int spaceSlots = words.length - 1;
        if (spaceSlots == 0 || totalSpacesNeeded <= 0) 
        {
            return hasRedLine ? "    " + String.join(" ", words) : String.join(" ", words);
        }
        
        int baseSpaces = totalSpacesNeeded / spaceSlots;
        int extraSpaces = totalSpacesNeeded % spaceSlots;
        
        StringBuilder justified = new StringBuilder();
        if (hasRedLine) 
        {
            justified.append("    ");
        }
        
        for (int i = 0; i < words.length; i++) 
        {
            justified.append(words[i]);
            if (i < words.length - 1) 
            {
                for (int j = 0; j < baseSpaces; j++) 
                {
                    justified.append(" ");
                }
                if (i < extraSpaces) 
                {
                    justified.append(" ");
                }
            }
        }
        
        return justified.toString();
    }
}