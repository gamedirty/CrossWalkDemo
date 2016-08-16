package com.lsh123.zxing.camera;

import com.lsh123.zxing.SourceData;

/**
 * Callback for camera previews.
 */
public interface PreviewCallback {
    void onPreview(SourceData sourceData);
}
