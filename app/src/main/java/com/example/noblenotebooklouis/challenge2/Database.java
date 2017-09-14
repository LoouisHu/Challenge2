package com.example.noblenotebooklouis.challenge2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Noble Notebook Louis on 14-Sep-17.
 */

public class Database {

    List<Beacon> anchors;

    public Database() {
        anchors = new ArrayList<Beacon>();
        anchors.add(new Beacon("FF22496F3253", new Position(967, 16)));
        anchors.add(new Beacon("FA72909FE2B0", new Position(896, 212)));
        anchors.add(new Beacon("cc42318a8421", new Position(807, 15)));
        anchors.add(new Beacon("da86f7ab4f84", new Position(989, 88)));
        anchors.add(new Beacon("e607914611cf", new Position(992, 206)));
        anchors.add(new Beacon("e0b3023852d4", new Position(791, 213)));
        anchors.add(new Beacon("E683B6DE681A", new Position(178, 190)));
        anchors.add(new Beacon("D3E7FCEF6937", new Position(285, 266)));
        anchors.add(new Beacon("DFBAE204BAAA", new Position(182, 528)));
        anchors.add(new Beacon("DBF8A2DE7172", new Position(268, 602)));
        anchors.add(new Beacon("DFD658036A02", new Position(281, 474)));
        anchors.add(new Beacon("C7AC35779BD2", new Position(472, 602)));
        anchors.add(new Beacon("C12E1029F913", new Position(470, 446)));
        anchors.add(new Beacon("f87fdf271601", new Position(282, 419)));
        anchors.add(new Beacon("c29fbcdedc9f", new Position(376, 417)));
        anchors.add(new Beacon("E8B4C06690E4", new Position(376, 332)));
        anchors.add(new Beacon("c60bdbb2091a", new Position(375, 246)));
        anchors.add(new Beacon("d38c818ddf4d", new Position(360, 192)));
        anchors.add(new Beacon("D7D0CF53AC7D", new Position(635, 612)));
        anchors.add(new Beacon("F67CC5550A5B", new Position(585, 534)));
        anchors.add(new Beacon("caab02073e20", new Position(791, 621)));
        anchors.add(new Beacon("c34e3e207f43", new Position(592, 441)));
        anchors.add(new Beacon("F81E0F3185078F", new Position(685, 440)));
        anchors.add(new Beacon("ef8fd1f1eac6", new Position(477, 320)));
        anchors.add(new Beacon("C0F912415FA9", new Position(577, 319)));
        anchors.add(new Beacon("E7266C14B846", new Position(683, 318)));
        anchors.add(new Beacon("CD222D536725", new Position(478, 208)));
        anchors.add(new Beacon("eb3ab70d8cec", new Position(181, 106)));
        anchors.add(new Beacon("DE866F2B7508", new Position(386, 125)));
        anchors.add(new Beacon("e181bb1945e4", new Position(479, 100)));
        anchors.add(new Beacon("E19CD455353C", new Position(581, 128)));
        anchors.add(new Beacon("f3e69d87e7ac", new Position(683, 122)));
        anchors.add(new Beacon("C0049E17BED9", new Position(684, 207)));
        anchors.add(new Beacon("DDC10842A0A5", new Position(180, 25)));
        anchors.add(new Beacon("CE9AFEF791C9", new Position(357, 21)));
        anchors.add(new Beacon("e597f7ec29eb", new Position(406, 18)));
        anchors.add(new Beacon("d76dfa463530", new Position(541, 17)));
        anchors.add(new Beacon("D7A93F4DE065", new Position(679, 33)));
        anchors.add(new Beacon("dd30132aacdf", new Position(766, 337)));
        anchors.add(new Beacon("f0288a8dca5d", new Position(797, 430)));
        anchors.add(new Beacon("EE0839973E44", new Position(810, 531)));
        anchors.add(new Beacon("f12996fa7b0f", new Position(897, 618)));
        anchors.add(new Beacon("c810f4815397", new Position(916, 543)));
        anchors.add(new Beacon("EEB3DCD0144F", new Position(977, 585)));
        anchors.add(new Beacon("E172CD69DFAE", new Position(896, 431)));
        anchors.add(new Beacon("f5aa53c380d3", new Position(989, 422)));
        anchors.add(new Beacon("C94331A61BAE", new Position(976, 320)));
        anchors.add(new Beacon("E49D85AEAFD7", new Position(790, 130)));
        anchors.add(new Beacon("df4ef940476e", new Position(908, 94)));

    }

    public List<Beacon> getAnchors() {
        return anchors;
    }

}
