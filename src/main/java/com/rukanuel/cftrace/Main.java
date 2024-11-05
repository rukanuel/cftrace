package com.rukanuel.cftrace;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Testing Trace with provider 'cloudflare' and key 'colo':");
            String traceResult = CfTrace.Trace("cloudflare", "colo");
            System.out.println("Trace result: " + traceResult);

            System.out.println("\nTesting Trace with provider '101' and no specific key:");
            traceResult = CfTrace.Trace("icanhazip","");
            System.out.println("Trace result: " + traceResult);

            System.out.println("\nTesting Geo with key 'clientIp':");
            String geoResult = CfTrace.Geo("clientIp");
            System.out.println("Geo result for clientIp: " + geoResult);

            System.out.println("\nTesting Geo without a specific key:");
            geoResult = CfTrace.Geo();
            System.out.println("Full Geo JSON response: " + geoResult);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
