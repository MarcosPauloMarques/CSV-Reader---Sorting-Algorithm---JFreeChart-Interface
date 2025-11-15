package view;
import javax.swing.*;  
import java.awt.*;
import java.util.List;

import controller.Analytics;
import controller.BubbleSortNestedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainInterface extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblSubs;
    private JLabel lblComps;

    private final List<List<String>> finalData;
    private final String[][] totalAreaPerState;
    private final String[][] totalAreaPerYear;

    public MainInterface(List<List<String>> data, String[][] areaState, String[][] areaYear) {
        this.finalData = data;
        this.totalAreaPerState = areaState;
        this.totalAreaPerYear = areaYear;

        setTitle("Monitor de Desmatamento da Amazônia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 640);
        setLocationRelativeTo(null);
        setResizable(false);

        BackgroundPanel background = new BackgroundPanel();
        background.setLayout(null);
        setContentPane(background);

        JLabel title = new JLabel("Monitor de Desmatamento da Amazônia", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.BLACK);
        title.setBounds(50, 30, 800, 40);
        background.add(title);

        JButton btnYear = criarBotao("Filtrar por Ano", 100, 100);
        JButton btnArea = criarBotao("Filtrar por Área (Devastação)", 500, 100);
        JButton btnState = criarBotao("Filtrar por Estado", 100, 180);
        JButton btnChartState = criarBotao("Gráfico por Estado", 500, 180);
        JButton btnChartYear = criarBotao("Gráfico por Ano", 300, 260);
        JButton btnSair = criarBotao("Sair", 300, 340);

        background.add(btnYear);
        background.add(btnArea);
        background.add(btnState);
        background.add(btnChartState);
        background.add(btnChartYear);
        background.add(btnSair);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 1));
        statsPanel.setBounds(0, 520, 900, 80);
        statsPanel.setBackground(new Color(220, 220, 220));
        background.add(statsPanel);

        lblSubs = new JLabel("Total de Substituições: " + Analytics.totalSubs, SwingConstants.CENTER);
        lblSubs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubs.setForeground(Color.BLACK);
        statsPanel.add(lblSubs);

        lblComps = new JLabel("Total de Comparações: " + Analytics.totalComps, SwingConstants.CENTER);
        lblComps.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblComps.setForeground(Color.BLACK);
        statsPanel.add(lblComps);

        JLabel footer = new JLabel("© Projeto Amazônia Viva", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footer.setForeground(Color.DARK_GRAY);
        footer.setBounds(0, 600, 900, 20);
        background.add(footer);

        btnYear.addActionListener(e -> {
            List<List<String>> sorted = BubbleSortNestedList.bubbleSortYear(finalData, true);
        	atualizarEstatisticas();
            mostrarTabela(sorted, "Filtro: Por Ano (Crescente) \n ANO | KM² | ESTADO");
        });

        btnArea.addActionListener(e -> {
            List<List<String>> sorted = BubbleSortNestedList.bubbleSortArea(finalData, false);
        	atualizarEstatisticas();
            mostrarTabela(sorted, "Filtro: Maior Devastação \n ANO | KM² | ESTADO");
        });

        btnState.addActionListener(e -> {
            List<List<String>> sorted = BubbleSortNestedList.bubbleSortState(finalData, true);
        	atualizarEstatisticas();
            mostrarTabela(sorted, "Filtro: Por Estado (A-Z) \n ANO | KM² | ESTADO");
        });
        
        btnSair.addActionListener(e -> {
            System.exit(0);
        });

        btnChartState.addActionListener(e -> mostrarGraficoEstado());
        btnChartYear.addActionListener(e -> mostrarGraficoAno());

        setVisible(true);
    }

    private void atualizarEstatisticas() {
        lblSubs.setText("Total de Substituições: " + Analytics.totalSubs);
        lblComps.setText("Total de Comparações: " + Analytics.totalComps);
    }

    private void mostrarTabela(List<List<String>> lista, String titulo) {
        StringBuilder sb = new StringBuilder(titulo + ":\n\n");
        for (List<String> l : lista) {
            sb.append(String.join(" | ", l)).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(800, 420));

        JOptionPane.showMessageDialog(this, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarGraficoAno() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String[] linha : totalAreaPerYear) {
            try {
                int valor = Integer.parseInt(linha[0]);
                String ano = linha[1];
                dataset.addValue(valor, "Desmatamento", ano);
            } catch (Exception ignored) {}
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico de Devastação por Ano",
                "Ano",
                "Área (km²)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new java.awt.Color(34, 139, 34)); // forest green

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("Gráfico de Devastação por Ano");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void mostrarGraficoEstado() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String[] linha : totalAreaPerState) {
            try {
                int valor = Integer.parseInt(linha[0]);
                String estado = linha[1];
                dataset.addValue(valor, "Desmatamento", estado);
            } catch (Exception ignored) {}
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico de Devastação por Estado",
                "Estado",
                "Área (km²)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new java.awt.Color(34, 139, 34)); // forest green

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("Gráfico de Devastação por Estado");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton criarBotao(String texto, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(240, 240, 240));
        btn.setForeground(Color.BLACK);
        btn.setBounds(x, y, 300, 50);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new RoundedBorder(20));

        Color corNormal = new Color(240, 240, 240);
        Color corHover = new Color(200, 200, 200);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(corHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(corNormal);
            }
        });

        return btn;
    }

    private static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            GradientPaint grad = new GradientPaint(
                    0, 0, Color.WHITE,
                    0, getHeight(), new Color(230, 230, 230)
            );
            g2d.setPaint(grad);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private static class RoundedBorder implements javax.swing.border.Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 2, radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.DARK_GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

}
