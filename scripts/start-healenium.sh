#!/bin/bash

# Healenium Backend Services Starter Script
echo "🚀 Starting Healenium Backend Services..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Error: Docker is not running. Please start Docker first."
    exit 1
fi

# Check if docker-compose.yml exists
if [ ! -f "docker-compose.yml" ]; then
    echo "❌ Error: docker-compose.yml not found. Run this script from project root."
    exit 1
fi

echo "📥 Pulling latest Healenium images..."
docker-compose pull

echo "🔧 Starting Healenium services..."
docker-compose up -d

echo "⏳ Waiting for services to start..."
sleep 30

# Check if services are running
echo "🔍 Checking service status..."

# Check PostgreSQL
if docker ps | grep -q "healenium-postgres"; then
    echo "✅ PostgreSQL Database: Running"
else
    echo "❌ PostgreSQL Database: Failed to start"
fi

# Check Backend
if docker ps | grep -q "healenium-backend"; then
    echo "✅ Healenium Backend: Running"
else
    echo "❌ Healenium Backend: Failed to start"
fi

# Check Proxy
if docker ps | grep -q "healenium-proxy"; then
    echo "✅ Healenium Proxy: Running"
else
    echo "❌ Healenium Proxy: Failed to start"
fi

# Check Selector Inspector
if docker ps | grep -q "healenium-selector-inspector"; then
    echo "✅ Selector Inspector: Running"
else
    echo "❌ Selector Inspector: Failed to start"
fi

echo ""
echo "🎉 Healenium services are starting up!"
echo ""
echo "📊 Access Points:"
echo "   • Backend API: http://localhost:7878"
echo "   • Proxy Service: http://localhost:8085" 
echo "   • Web UI (Reports): http://localhost:8080"
echo "   • Database: localhost:5432"
echo ""
echo "📋 Next Steps:"
echo "   1. Update config.properties: healenium.enabled=true"
echo "   2. Run your tests: mvn test"
echo "   3. View healing reports: http://localhost:8080"
echo ""
echo "🛑 To stop services: ./scripts/stop-healenium.sh" 