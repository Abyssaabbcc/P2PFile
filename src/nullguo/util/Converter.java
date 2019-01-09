package nullguo.util;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.util.StreamUtils;

public class Converter extends AbstractHttpMessageConverter<String> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private volatile List<Charset> availableCharsets;
    private boolean writeAcceptCharset;

    public Converter() {
        this(DEFAULT_CHARSET);
    }

    public Converter(Charset defaultCharset) {
        super(defaultCharset, new MediaType[]{MediaType.TEXT_PLAIN, MediaType.ALL});
        this.writeAcceptCharset = true;
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }

    public boolean supports(Class<?> clazz) {
        return String.class == clazz;
    }

    protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {
        Charset charset = this.getContentTypeCharset(inputMessage.getHeaders().getContentType());
        return StreamUtils.copyToString(inputMessage.getBody(), charset);
    }

    protected Long getContentLength(String str, MediaType contentType) {
        Charset charset = this.getContentTypeCharset(contentType);

        try {
            return Long.valueOf((long)str.getBytes(charset.name()).length);
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException(var5);
        }
    }

    protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {
        if(this.writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(this.getAcceptedCharsets());
        }

        Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());
        StreamUtils.copy(str, charset, outputMessage.getBody());
    }

    protected List<Charset> getAcceptedCharsets() {
        if(this.availableCharsets == null) {
            this.availableCharsets = new ArrayList(Charset.availableCharsets().values());
        }

        return this.availableCharsets;
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        return contentType != null && contentType.getCharset() != null?contentType.getCharset():this.getDefaultCharset();
    }
}
