import java.awt.BorderLayout;
import java.nio.FloatBuffer;

import javax.swing.JFrame;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;

public class Hello implements GLEventListener {
    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4();
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT);

        float[] colors = { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        float[] vertices = { 0.0f, 0.5f, 0.0f, 0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f };

        String vertexSource = "#version 460 core                            \n"
                + "layout(location = 0) in  vec3 position;      \n" + "layout(location = 1) in  vec3 color;         \n"
                + "out vec4 vColor;                             \n" + "void main()                                  \n"
                + "{                                            \n" + "  vColor = vec4(color, 1.0);                 \n"
                + "  gl_Position = vec4(position, 1.0);         \n" + "}                                            \n";
        String fragmentSource = "#version 460 core                            \n"
                + "precision mediump float;                     \n" + "in  vec4 vColor;                             \n"
                + "out vec4 outColor;                           \n" + "void main()                                  \n"
                + "{                                            \n" + "  outColor = vColor;                         \n"
                + "}                                            \n";

        int[] vbo = new int[2];
        gl.glGenBuffers(2, vbo, 0);

        FloatBuffer vertexBuffer = Buffers.newDirectFloatBuffer(vertices);
        FloatBuffer vertexColorBuffer = Buffers.newDirectFloatBuffer(colors);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[0]);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * vertices.length, vertexBuffer, GL4.GL_STATIC_DRAW);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[1]);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, 4 * colors.length, vertexColorBuffer, GL4.GL_STATIC_DRAW);

        int vs = gl.glCreateShader(GL4.GL_VERTEX_SHADER);
        int fs = gl.glCreateShader(GL4.GL_FRAGMENT_SHADER);

        String[] vsrc = new String[] { vertexSource };
        int[] vlengths = new int[] { vsrc[0].length() };
        gl.glShaderSource(vs, 1, vsrc, vlengths, 0);
        gl.glCompileShader(vs);

        String[] fsrc = new String[] { fragmentSource };
        int[] flengths = new int[] { fsrc[0].length() };
        gl.glShaderSource(fs, 1, fsrc, flengths, 0);
        gl.glCompileShader(fs);

        int program = gl.glCreateProgram();
        gl.glAttachShader(program, vs);
        gl.glAttachShader(program, fs);
        gl.glLinkProgram(program);
        gl.glUseProgram(program);

        int posAttrib = gl.glGetAttribLocation(program, "position");
        gl.glEnableVertexAttribArray(posAttrib);

        int colAttrib = gl.glGetAttribLocation(program, "color");
        gl.glEnableVertexAttribArray(colAttrib);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[0]);
        gl.glVertexAttribPointer(posAttrib, 3, GL4.GL_FLOAT, false, 0, 0);

        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[1]);
        gl.glVertexAttribPointer(colAttrib, 3, GL4.GL_FLOAT, false, 0, 0);

        gl.glDrawArrays(GL4.GL_TRIANGLES, 0, 3);
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL4);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // final GLCanvas glcanvas = new GLCanvas(capabilities);
        GLJPanel gljpanel = new GLJPanel(capabilities);

        Hello hello = new Hello();
        // glcanvas.addGLEventListener(hello);
        gljpanel.addGLEventListener(hello);
        // glcanvas.setSize(640, 480);
        gljpanel.setSize(640, 480);

        final JFrame frame = new JFrame("Hello, World!");
        frame.getContentPane().add(gljpanel, BorderLayout.CENTER);
        frame.setSize(640, 480);
        // frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
