package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class GPXcalculatorTest {
	
	private Date _date = new Date();
	private GPXtrkpt _pt1, _pt2, _pt3, _pt4, _pt5, _pt6, _pt7, _pt8, _pt9, _pt10, _pt11, _pt12, _pt13, _pt14, _pt15;
	private GPXtrkseg _seg1, _seg2, _seg3, _seg4;
	private GPXtrk _trk;
	private ArrayList<GPXtrkpt> _trkpts1, _trkpts2, _trkpts3, _trkpts4, _trkpts5;
	private ArrayList<GPXtrkseg> _trkseg;

	@Before
	public void setUp() throws Exception {
		_pt1 = null;
		_pt2 = new GPXtrkpt(70, 120, _date); 
		_pt3 = new GPXtrkpt(100, 100, _date);
		_pt4 = new GPXtrkpt(90, 180, _date); 
		_pt5 = new GPXtrkpt(-90, -180, _date); 
		_pt6 = new GPXtrkpt(-100, -100, _date);
		_pt7 = new GPXtrkpt(0, 270, _date);
		_pt8 = new GPXtrkpt(0, -270, _date);
		_pt9 = new GPXtrkpt(0, 0, _date);
		_pt10 = new GPXtrkpt(45, 45, _date); 
		_pt11 = new GPXtrkpt(-45, -45, _date); 
		_pt12 = new GPXtrkpt(60, -30, _date);
		_pt13 = new GPXtrkpt(-20, 0, _date); 
		_pt14 = new GPXtrkpt(30, -10, _date); 
		_pt15 = new GPXtrkpt(0, 50, _date); 
		_trkpts1 = null;
		_trkpts2 = new ArrayList<GPXtrkpt>();
		_trkpts3 = new ArrayList<GPXtrkpt>(); 
		_trkpts4 = new ArrayList<GPXtrkpt>(); 
		_trkpts5 = new ArrayList<GPXtrkpt>(); 
		_seg1 = null;
		_trkseg = null;
		_trk = null;
		
	}

	@Test
	public void testNullGPXtrk() {
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), -1, 0);
	}
	
	@Test
	public void testNoSegGPXtrk() {
		_trk = new GPXtrk("No segs", null);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), -1, 0);
	}
	
	@Test
	public void testNormalGPXtrk() {
		_trkpts3.add(_pt13);
		_trkpts3.add(_pt10);
		_trkpts4.add(_pt14);
		_trkpts4.add(_pt2);
		_trkpts5.add(_pt9);
		_trkpts5.add(_pt11);
		_seg2 = new GPXtrkseg(_trkpts3);
		_seg3 = new GPXtrkseg(_trkpts4);
		_seg4 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trkseg.add(_seg4);
		_trk = new GPXtrk("Normal", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 278.711, .001);
	}

	@Test
	public void testOnlyNullGPXtrkseg() {
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg1);
		_trk = new GPXtrk("Null GPXtrkseg", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 0, 0);
	}

	@Test
	public void testNullGPXtrkseg() {
		_trkpts4.add(_pt9);
		_trkpts4.add(_pt10);
		_trkpts4.add(_pt15);
		_trkpts5.add(_pt2);
		_trkpts5.add(_pt4);
		_trkpts5.add(_pt5);
		_seg1 = new GPXtrkseg(_trkpts1);
		_seg2 = new GPXtrkseg(_trkpts4);
		_seg3 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg1);
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("One is null", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 574.654, .001);
	}

	@Test
	public void testOneEmptyGPXtrkseg() {
		_trkpts4.add(_pt9);
		_trkpts4.add(_pt2);
		_trkpts4.add(_pt5);
		_seg2 = new GPXtrkseg(_trkpts2);
		_seg3 = new GPXtrkseg(_trkpts4);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("One is Empty", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 478.924, .001);
	}

	@Test
	public void testOnlyEmptyGPXtrkseg() {
		_seg2 = new GPXtrkseg(_trkpts2);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg2);
		_trk = new GPXtrk("Empty GPXtrkseg", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 0, 0);
	}

	@Test
	public void testOnePtGPXtrkseg() {
		_trkpts4.add(_pt9);
		_trkpts5.add(_pt10);
		_trkpts5.add(_pt14);
		_seg2 = new GPXtrkseg(_trkpts4);
		_seg3 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("Single point", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 57.009, .001);
	}

	@Test
	public void testNullGPXtrkpt() {
		_trkpts4.add(_pt1);
		_trkpts4.add(_pt12);
		_trkpts4.add(_pt14);
		_trkpts5.add(_pt10);
		_trkpts5.add(_pt13);
		_trkpts5.add(_pt5);
		_seg2 = new GPXtrkseg(_trkpts4);
		_seg3 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("Null point", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 115.112, .001);
	}

	@Test
	public void testInvalidLatGPXtrkpt() {
		_trkpts4.add(_pt3);
		_trkpts4.add(_pt4);
		_trkpts4.add(_pt15);
		_trkpts5.add(_pt6);
		_trkpts5.add(_pt10);
		_trkpts5.add(_pt14);
		_seg2 = new GPXtrkseg(_trkpts4);
		_seg3 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg1);
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("One is null", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 215.123, .001);
	}

	@Test
	public void testInvalidLongGPXtrkpt() {
		_trkpts4.add(_pt9);
		_trkpts4.add(_pt7);
		_trkpts4.add(_pt15);
		_trkpts5.add(_pt8);
		_trkpts5.add(_pt4);
		_trkpts5.add(_pt12);
		_seg2 = new GPXtrkseg(_trkpts4);
		_seg3 = new GPXtrkseg(_trkpts5);
		_trkseg = new ArrayList<GPXtrkseg>();
		_trkseg.add(_seg1);
		_trkseg.add(_seg2);
		_trkseg.add(_seg3);
		_trk = new GPXtrk("One is null", _trkseg);
		assertEquals(GPXcalculator.calculateDistanceTraveled(_trk), 262.132, .001);
	}

}
