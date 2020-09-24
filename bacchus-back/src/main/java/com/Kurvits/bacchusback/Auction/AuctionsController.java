package com.Kurvits.bacchusback.Auction;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuctionsController {


    @GetMapping("/api")
    JSONArray getAuctions() throws IOException, ParseException {
        return Auction.fetchAuctions();
    }
}