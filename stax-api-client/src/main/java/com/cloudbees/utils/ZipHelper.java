/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package com.cloudbees.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

    /**
     * Recursively inserts all files in a directory into a zipstream.
     *
     * @param directory
     *            the source directory
     * @param base
     *            optional parent directory that should serve as the entry root.
     *            Only path info after this base will be included as part of the
     *            entry name. By default, the directory parameter serves as
     *            root.
     * @param dirPrefix
     *            optional directory prefix to prepend onto each entry name.
     * @param zos
     *            the zip stream to add the files to.
     * @throws java.io.IOException
     */
    public static final void addDirectoryToZip(File directory, File base,
            String dirPrefix, ZipOutputStream zos) throws IOException {
        if (base == null)
            base = directory;
        if (dirPrefix == null)
            dirPrefix = "";

        // add an entry for the directory itself
        if (!base.equals(directory)) {
            String dirEntryPath = dirPrefix
                    + directory.getPath()
                            .substring(base.getPath().length() + 1).replace(
                                    '\\', '/');
            ZipEntry dirEntry = new ZipEntry(
                    dirEntryPath.endsWith("/") ? dirEntryPath : dirEntryPath
                            + "/");
            zos.putNextEntry(dirEntry);
        }

        File[] files = directory.listFiles();
        byte[] buffer = new byte[8192];
        int read = 0;
        for (int i = 0, n = files.length; i < n; i++) {
            // if (!files[i].isHidden()) {
            if (files[i].isDirectory()) {
                addDirectoryToZip(files[i], base, dirPrefix, zos);
            } else {
                FileInputStream in = new FileInputStream(files[i]);
                ZipEntry entry = new ZipEntry(dirPrefix
                        + files[i].getPath().substring(
                                base.getPath().length() + 1).replace('\\', '/'));
                entry.setTime(files[i].lastModified());
                zos.putNextEntry(entry);
                while (-1 != (read = in.read(buffer))) {
                    zos.write(buffer, 0, read);
                }
                in.close();
            }
            // }
        }
    }


    private static int BUFFER = 2048;

    public static File unzipEntryToFolder(ZipEntry entry, InputStream zis, File destFolder) throws IOException {
        BufferedOutputStream dest;
        if (entry.isDirectory()) {
            File destFile = new File(destFolder, entry.getName());
            destFile.mkdirs();
            return destFile;
        } else {
            int count;
            byte data[] = new byte[BUFFER];
            // write the files to the disk
            File destFile = new File(destFolder, entry.getName());
            File parentFolder = destFile.getParentFile();
            if (!parentFolder.exists())
                parentFolder.mkdirs();
            FileOutputStream fos = new FileOutputStream(destFile);
            dest = new BufferedOutputStream(fos, BUFFER);
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();

            return destFile;
        }
    }
}
